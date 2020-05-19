$( document ).ready(function(){
    console.log("document ready");

    function getUrlWithParams(key, value){
        var url = window.location.href;
        if(url.indexOf('?') > -1){
            url += "&" + key + "=" + value
        }else{
            url += "?" + key + "=" + value
        }

        return url;
    }

    function removeURLParameter(url, parameter) {
        //prefer to use l.search if you have a location/link object
        var urlparts= url.split('?');
        if (urlparts.length>=2) {

            var prefix= encodeURIComponent(parameter)+'=';
            var pars= urlparts[1].split(/[&;]/g);

            //reverse iteration as may be destructive
            for (var i= pars.length; i-- > 0;) {
                //idiom for string.startsWith
                if (pars[i].lastIndexOf(prefix, 0) !== -1) {
                    pars.splice(i, 1);
                }
            }

            url= urlparts[0]+'?'+pars.join('&');
            return url;
        } else {
            return url;
        }
    }

    $("#updateTeamName").on("submit", function(e){
        e.preventDefault();
        var json = {};
        var arr = $(this).serializeArray();
        $.each(arr, function(){
            var name = this.name.split(":")[1];
            if(name !== undefined){
                json[name] = this.value;
            }
        });
        var data = JSON.stringify(json);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "PUT",
            url: "/bballLeague/api/team/" + json.id,
            data: data,
            success: function(response){
                window.location.href = removeURLParameter(window.location.href, "error");
            },
            error: function(response){
                console.log(response);
                window.location.href = getUrlWithParams("error", "true");
            }
        });
    });
});