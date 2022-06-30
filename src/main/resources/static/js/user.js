let index = {
    init: function () {
        // jQuery 사용
        $("#btn-save").on("click", () => {
            this.save(); // save함수 이벤트로 호출
        });

    },

    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/api/joinProc",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            alert("회원가입이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },
}

index.init();