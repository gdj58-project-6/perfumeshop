<!DOCTYPE html>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
        <style type="text/css">
        
        body {
            font-family:"Malgun Gothic";
            font-size: 0.8em;

        }
        /*TAB CSS*/

        ul.tabs {
            margin: 0;
            padding: 0;
            float: left;
            list-style: none;
            height: 32px; /*--Set height of tabs--*/
            border-bottom: 1px solid #999;
            border-left: 1px solid #999;
            width: 100%;
        }
        ul.tabs li {
            float: left;
            margin: 0;
            padding: 0;
            height: 31px; /*--Subtract 1px from the height of the unordered list--*/
            line-height: 31px; /*--Vertically aligns the text within the tab--*/
            border: 1px solid #999;
            border-left: none;
            margin-bottom: -1px; /*--Pull the list item down 1px--*/
            overflow: hidden;
            position: relative;
            background: #e0e0e0;
        }
        ul.tabs li a {
            text-decoration: none;
            color: #000;
            display: block;
            font-size: 1.2em;
            padding: 0 20px;
            /*--Gives the bevel look with a 1px white border inside the list item--*/
            border: 1px solid #fff; 
            outline: none;
        }
        ul.tabs li a:hover {
            background: #ccc;
        }
        html ul.tabs li.active, html ul.tabs li.active a:hover  {
             /*--Makes sure that the active tab does not listen to the hover properties--*/
            background: #fff;
            /*--Makes the active tab look like it's connected with its content--*/
            border-bottom: 1px solid #fff; 
        }

        /*Tab Conent CSS*/
        .tab_container {
            border: 1px solid #999;
            border-top: none;
            overflow: hidden;
            clear: both;
            float: left; 
            width: 100%;
            background: #fff;
        }
        .tab_content {
            padding: 20px;
            font-size: 1.2em;
        }
    </style>
        
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