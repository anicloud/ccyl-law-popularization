#!/bin/bash
p="/home/anicloud/db"
dbUser='anicloud'
dbPassword='Anicl0ud'
dbName='ccyleg_14'
host='rdsp1t16146w99vl6519.mysql.rds.aliyuncs.com'

cd $p;
for f in `ls $p`
do
echo $f;
mysql -h $host -u $dbUser -p$dbPassword -f $dbName -e "source $f";
mv $f $f.done;
done
echo 'finished!'