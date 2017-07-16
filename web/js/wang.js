/**
 *  by wangrongjun on 2017/7/6.
 */

/**
 * @param updateMap 需要更新的参数列表。格式样例：var map={key1:"value1", key2:"value2"};
 * @return string 参数更新后的url
 */
function updateParamInUrl(oldUrl, updateMap) {
    for (var key in updateMap) {
        var value = updateMap[key];
        if (value === null || value === "") {//如果值为空，则没必要添加进url的参数列
            continue;
        } else if (oldUrl.indexOf(key) != -1) {//如果key存在
            oldUrl = oldUrl.replace(new RegExp(key + "=[^&]+"), key + "=" + value);
        } else if (oldUrl.indexOf("?") != -1) {//如果?存在，即有别的参数，添加到参数后面
            oldUrl = oldUrl + "&" + key + "=" + value;
        } else {//如果没有参数，直接加在后面
            oldUrl += "?" + key + "=" + value;
        }
    }
    return oldUrl;
}

/**
 * @param setMap 需要设置的参数列表。格式样例：var map={key1:"value1", key2:"value2"};
 * @return string 参数设置后的url。注意：原先在url的参数如果不在map中，则会删除。
 */
function setParamInUrl(oldUrl, setMap) {
    var index = oldUrl.indexOf("?");
    if (index != -1) {
        return updateParamInUrl(oldUrl.substring(0, index), setMap);
    } else {
        return updateParamInUrl(oldUrl, setMap);
    }
}