<!DOCTYPE html>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
    </head>
    <body>
        <div id="box">
            <li class="main">
                test
                <ul class="sub" style="display:none">
                    <li>test1</li>
                    <il>test2</il>
                </ul>
            </ul>
        </div>
        <script>
            $(".main").click(function(){
                if($(".sub").is(":visible")){
                    $(".sub").slideUp();
                }
                else{
                    $(".sub").slideDown();
                }
            })
        </script>
    </body>
</html>