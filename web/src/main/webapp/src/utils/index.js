export function getSearchString(key) {
    // 获取URL中?之后的字符
    let str = location.search;
    str = str.substring(1,str.length);
     // 以&分隔字符串，获得类似name=xiaoli这样的元素数组
    let arr = str.split("&");
    let obj = {};
     // 将每一个数组元素以=分隔并赋给obj对象
    for(let i = 0; i < arr.length; i++) {
        let tmp_arr = arr[i].split("=");
        obj[decodeURIComponent(tmp_arr[0])] = decodeURIComponent(tmp_arr[1]);
    }
    return obj[key];
}