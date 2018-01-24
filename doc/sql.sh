#!/bin/bash

#### change the values below where needed.....
#### 多数据库DBNAMES="MyDb1 MyDb2 MyDb3"
DBNAMES="ccyleg_14"
HOST="--host=rdsp1t16146w99vl6519.mysql.rds.aliyuncs.com"
USER="--user=anicloud"
PASSWORD="--password=Anicl0ud"
BACKUP_DIR="/home/anicloud/db"

#### you can change these values but they are optional....
OPTIONS="--default-character-set=utf8 --complete-insert --no-create-info --compact -q"
DATE=`/bin/date '+%y%m%d_%H%M%S'`

#### 分表的数据，暂时只导出最新那个月的数据
NOW_MONTH=`/bin/date '+%Y%m'`

#### make no changes after this....
#### start script ####
echo removing old temporary files if they exists...
rm -f ${BACKUP_DIR}/*.sql > /dev/null 2>&1
rm -f ${BACKUP_DIR}/*.tar > /dev/null 2>&1
cd ${BACKUP_DIR}

for DB in $DBNAMES
do
    echo "=========================================="
    echo ${DB}
    echo "=========================================="
    echo 'SET FOREIGN_KEY_CHECKS=0;' > $RESTORESCRIPT

    #### 导出表数据
    for TABLE in `mysql $HOST $USER $PASSWORD $DB -e 'show tables' | egrep -v 'Tables_in_' `;
    do
        TABLENAME=$(echo $TABLE|awk '{ printf "%s", $0 }')
        FILENAME="${TABLENAME}.sql"

        #### 正则忽略 && 当前月数据忽略
        if [[ $TABLENAME = david_*_tmp ]] || [[ $TABLENAME =~ 'david_trade_detail_' && $TABLENAME != "david_trade_detail_${NOW_MONTH}" ]]
        then
            echo ${TABLENAME} '忽略';
            continue
        else
            echo Dumping ${TABLENAME}
            echo 'source ' $BACKUP_DIR'/'$FILENAME';' >> $RESTORESCRIPT
            mysqldump $OPTIONS $HOST $USER $PASSWORD $DB ${TABLENAME} > ${BACKUP_DIR}/${FILENAME}
        fi
    done

    #### 压缩
:<<aaa
    echo 'SET FOREIGN_KEY_CHECKS=1;' >> $RESTORESCRIPT

    echo making tar...
    tar -cf ${DB}_${DATE}.tar *.sql  > /dev/null 2>&1

    echo compressing...
    gzip -9 ${DB}_${DATE}.tar > /dev/null 2>&1

    echo removing temporary files...
    rm -f ${BACKUP_DIR}/*.sql > /dev/null 2>&1
    rm -f ${BACKUP_DIR}/*.tar > /dev/null 2>&1
aaa

    echo "done with " $DB
done

echo "=========================================="
echo "            done with all database!       "
echo "=========================================="