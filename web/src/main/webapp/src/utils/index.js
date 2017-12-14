export function getSearchString(key) {
    // 获取URL中?之后的字符
    let str = window.location.search;
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

export function jsSdkConfig(axios, host) {
    axios.get(`${host}/wechat/getJsSDKConfig?timestamp=${new Date().getTime()}&nonceStr=nonceStr&url=${window.location.href}`).then(function (response) {
        if (response.data.state === 0) {
            /*配置微信jssdk*/
            window.wx.config({
                debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: response.data.data.appId, // 必填，企业号的唯一标识，此处填写企业号corpid
                timestamp: response.data.data.timestamp, // 必填，生成签名的时间戳
                nonceStr: response.data.data.nonceStr, // 必填，生成签名的随机串
                signature: response.data.data.signature,// 必填，签名，见附录1
                jsApiList: [
                    'getLocation'
                ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
            });
        }
    }).catch(function (errors) {
        console.log('errors', errors);
    });
}