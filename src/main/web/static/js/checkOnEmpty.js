function checkOnEmpty(document) {
    $(document).ready(function(){
        let checkField;
        checkField = $("#dateOfBirth").val().length;

        let enableDisableButton = function(){
            if(checkField > 0){
                $('#submit').removeAttr("disabled");
            }
            else {
                $('#submit').prop("disabled","disabled");
            }
        };
        enableDisableButton();
        $('input#dateOfBirth').keyup(function(){
            checkField = $("input#dateOfBirth").val().length;
            enableDisableButton();
        });
    });
}