
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>抢红包高并发</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.0.js">

    </script>
  </head>
  <body>
  <script type="text/javascript">
    $(document).ready(function(){
        // 模拟30000个异步请求
        var max = 30000;
        for(var i =1; i<max; i++){
          // jquery async post request
          $.post({
            url:"./userRedPacket/grapRedPacketForVersionRepeatForFrequency.do?redPacketId=5&userId="+i,
            success:function(data){
            }

          });
        }
    });
  </script>
  </body>
</html>
