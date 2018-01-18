export function getSearchString(search, key) {
    // 获取URL中?之后的字符
    let str = search.substring(1, search.length);
    // 以&分隔字符串，获得类似name=xiaoli这样的元素数组
    let arr = str.split("&");
    let obj = {};
    // 将每一个数组元素以=分隔并赋给obj对象
    for (let i = 0; i < arr.length; i++) {
        let tmp_arr = arr[i].split("=");
        obj[decodeURIComponent(tmp_arr[0])] = decodeURIComponent(tmp_arr[1]);
    }
    return obj[key];
}

export function jsSdkConfig(axios, host, count) {
    let u = window.navigator.userAgent;
    let isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
    let isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    let iosVersion = isiOS ? window.parseInt(u.match(/\(iPhone; CPU iPhone OS ([0-9]+)_.+Mac OS X/)[1]) : '';
    let url = '';
    // alert(iosVersion);
    // alert(u);
    // alert(window.location.href.split('#')[0]);
    if (isiOS) {
        if (iosVersion >= 11) {
            alert(1);
            /*if (count === 2) {
                url = 'http://www.12355.org.cn/leg/'; // 1
            } else if (count === 3) {
                url = window.sessionStorage.getItem('option');
            } else if (count === 4) {
                url = window.location.href.split('#')[0];
            } else {
                url = 'http://www.12355.org.cn/leg/thumb'; // 2
            }*/
            let status = window.sessionStorage.getItem('status');
            if (status === 'LOGIN_SUCCESS') {
                url = 'http://www.12355.org.cn/leg/';
            } else if (status === 'THUMB_UP') {
                url = 'http://www.12355.org.cn/leg/thumb';
            } else {
                url = window.sessionStorage.getItem('option');
            }
        } else {
                url = window.sessionStorage.getItem('option'); // LOGIN_SUCCESS
        }
    } else {
            url = window.location.href.split('#')[0];
    }
    let resultUrl = "";
    if (url.indexOf("from") !== -1) {
        let strs = url.split("from");
        if (strs[0].lastIndexOf("?") === strs[0].length - 1) {
            resultUrl = strs[0].split("?")[0];
        } else if (strs[0].lastIndexOf("&") === strs[0].length - 1) {
            resultUrl = strs[0].substring(0, strs[0].length - 1);
        } else {
            resultUrl = strs[0];
        }
    } else {
        resultUrl = url;
    }
    /*window.location.href = resultUrl;*/
    alert(resultUrl);
    resultUrl = encodeURIComponent(resultUrl);
    let time = Math.round(new Date().getTime() / 1000);
    // alert(window.location.href.split('#')[0]);
    axios.get(`${host}/wechat/getJsSDKConfig?timestamp=${time}&nonceStr=nonceStr&url=${resultUrl}`).then(function (response) {
        if (response.data.state === 0) {
            /*配置微信jssdk*/
            window.wx.config({
                debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: response.data.data.appId, // 必填，企业号的唯一标识，此处填写企业号corpid
                timestamp: window.parseInt(response.data.data.timestamp), // 必填，生成签名的时间戳
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
    for (let i = 0; i < arrCookie.length; i++) {
        let arr = arrCookie[i].split("=");
        if (name === arr[0]) {
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