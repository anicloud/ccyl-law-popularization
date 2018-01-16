export function getSearchString(search, key) {
    // 获取URL中?之后的字符
    let str = search.substring(1,search.length);
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
    let u = window.navigator.userAgent;
    let isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
    let isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    let url = '';
    if (isiOS) {
        url = encodeURIComponent('http://www.12355.org.cn/leg/home/index?op=LOGIN_SUCCESS');
    } else {
        url = encodeURIComponent(window.location.href.split('#')[0]);
    }

    let time = Math.round(new Date().getTime() / 1000);
    // alert(window.location.href.split('#')[0]);
    axios.get(`${host}/wechat/getJsSDKConfig?timestamp=${time}&nonceStr=nonceStr&url=${url}`).then(function (response) {
        if (response.data.state === 0) {
            /*配置微信jssdk*/
            window.wx.config({
                debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: response.data.data.appId, // 必填，企业号的唯一标识，此处填写企业号corpid
                timestamp: response.data.data.timestamp, // 必填，生成签名的时间戳
                nonceStr: response.data.data.nonceStr, // 必填，生成签名的随机串
                signature: response.data.data.signature,// 必填，签名，见附录1
                jsApiList: [
                    'getLocation',
                    'onMenuShareTimeline',
                    'onMenuShareAppMessage'
                ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
            });
        }
    }).catch(function (errors) {
        console.log('errors', errors);
    });
}

export function getCookie(name) {
    let strCookie = document.cookie;
    let arrCookie = strCookie.split("; ");
    for(let i = 0; i < arrCookie.length; i++){
        let arr = arrCookie[i].split("=");
        if(name === arr[0]){
            return arr[1];
        }
    }
    return "";
}

export function getPartName(name) {
    if (name.length > 3) {
        return name.substr(0, 3) + '...';
    }
    return name;
}