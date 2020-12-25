
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>抢红包高并发</title>
    <script type="text/javascript" src="/WEB-INF/js/jquery-3.5.1.min.js">

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
            url:"./userRedPacket/grepRedPacket.do?redPacketId=1&userId="+i,
            success:function(data){

            }

          });
        }
    });
  </script>
  </body>
</html>
