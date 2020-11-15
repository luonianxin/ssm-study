import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",16379);
        long start = System.currentTimeMillis();
        int i=1000000;
     try{

            while(true){
                long end = System.currentTimeMillis();
                if(end - start >1000000){
                    break;
                }
                i++;
                jedis.setex("test"+i,1800,i+"");
            }
    }finally {
         jedis.close();
     }
        System.out.println("redis 每秒操作:"+ i +" 次");
    }
    @Test
    public void testJedisPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(50);
        config.setMaxTotal(100);
        config.setMaxWaitMillis(20000);
        JedisPool  pool = new JedisPool(config,"localhost",16379);
        Jedis jedis = pool.getResource();
        System.out.println(jedis.get("test11"));
    }
}
