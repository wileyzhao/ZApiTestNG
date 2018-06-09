package librarys.database;

import librarys.log.logLib;
import redis.clients.jedis.*;

/**
 * Created by wanli on 17/1/20.
 */
public class RedisDataSource{

    private static JedisPool jedisPool = null;
    //Redis服务器IP
    private static String ADDR = "127.0.0.1";
    //Redis的端口号
    private static int PORT = 6379;
    //访问密码
    private static String AUTH = "admin";
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;
    private static int TIMEOUT = 10000;
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    public static  Jedis getRedisClient() {
        if(jedisPool == null){
            initJedisPool(ADDR,PORT);
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis;
        } catch (Exception e) {
            logLib.logError("getRedisClent error: "+ e);
        }
        return null;
    }

    /**
     * 新建并初始化jedis线程池
     * @param addr  redis的ip
     * @param port  redis的端口号
     */
    public  static void initJedisPool(String addr, int port) {
        try{
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            if (!"".equals(addr))
                ADDR = addr;
            if (!"".equals(port))
                PORT = port;
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void returnResource(Jedis jedis, boolean broken) {
        if(jedis != null)
            jedis.close();
    }


    public static void closePool() {
        if(jedisPool != null){
            jedisPool.close();
        }
    }

    /**
     * 设置单个值
     *
     * @param key
     * @param value
     * @return
     */
    public static String set(String key, String value) {
        String result = null;

        Jedis jedis = getRedisClient();
        if (jedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = jedis.set(key, value);
        } catch (Exception e) {
            logLib.logError(e.getMessage()+" : " +e);
            broken = true;
        } finally {
            returnResource(jedis, broken);
        }
        return result;
    }

    /**
     * 获取单个值
     *
     * @param key
     * @return
     */
    public  static String get(String key) {
        String result = null;
        Jedis jedis = getRedisClient();
        if (jedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = jedis.get(key);

        } catch (Exception e) {
            logLib.logError(e.getMessage()+" : " +e);
            broken = true;
        } finally {
            returnResource(jedis, broken);
        }
        return result;
    }

    public static Boolean exists(String key) {
        Boolean result = false;
        Jedis jedis = getRedisClient();
        if (jedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = jedis.exists(key);
        } catch (Exception e) {
            logLib.logError(e.getMessage()+" : " +e);
            broken = true;
        } finally {
            returnResource(jedis, broken);
        }
        return result;
    }


    public String getSet(String key, String value) {
        String result = null;
        Jedis jedis = getRedisClient();
        if (jedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = jedis.getSet(key, value);
        } catch (Exception e) {
            logLib.logError(e.getMessage()+" : " +e);
            broken = true;
        } finally {
            returnResource(jedis, broken);
        }
        return result;
    }
}
