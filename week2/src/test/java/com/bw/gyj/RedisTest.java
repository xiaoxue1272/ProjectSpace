package com.bw.gyj;

import com.bw.utils.StringUtil;
import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * author:xiaoxue1272
 * date:2019/12/9-08:45
 */
public class RedisTest {


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-redis.xml");
        redisTemplate = context.getBean(RedisTemplate.class);
        jdk();
        json();
        hash();
    }


    private static RedisTemplate<String,Object> redisTemplate;

    //生成10万个数据对象
    public static List<User> getData(){
        List<User> list = new ArrayList<>();
        for (int i = 1 ; i <= 50000 ; i ++ ){
            User user = new User();
            user.setId(String.valueOf(i));
            user.setName(StringUtil.getChinese(3));
            user.setSex(((int)(Math.random()*10))%2==0?"男":"女");
            user.setPhone("13"+String.valueOf(StringUtil.getRandom(9)));
            int size = (int)(Math.random()*20);
            if (size<3){
                size+=3;
            }
            user.setEmail(StringUtil.getRandomNumberAndCharacter(size)+"@qq.com");
            int birthday = (int)(Math.random()*70);
            if (birthday<18){
                birthday+=18;
            }
            birthday=1931+birthday;
            user.setBirthday(String.valueOf(birthday));
            list.add(user);
        }
        return list;
    }

    //jdk序列化
    public static void jdk(){
        BoundListOperations<String, Object> jdk = redisTemplate.boundListOps("jdk");
        List<User> list = getData();
        long begin = System.currentTimeMillis();
        jdk.leftPush(list);
        long end = System.currentTimeMillis();
        System.out.println("保存方式：jdk");
        System.out.println("保存数量："+list.size());
        System.out.println("所用时间："+(end-begin)+"毫秒");
    }

    //json序列化
    public static void json(){
        BoundListOperations<String, Object> json = redisTemplate.boundListOps("json");
        List<User> list = getData();
        long begin = System.currentTimeMillis();
        json.leftPush(new Gson().toJson(list));
        long end = System.currentTimeMillis();
        System.out.println("保存方式：json");
        System.out.println("保存数量："+list.size());
        System.out.println("所用时间："+(end-begin)+"毫秒");
    }

    //hash序列化
    public static void hash(){
        BoundHashOperations<String, Object, Object> hash = redisTemplate.boundHashOps("hash");
        List<User> list = getData();
        long begin = System.currentTimeMillis();
        hash.put("hash",list);
        long end = System.currentTimeMillis();
        System.out.println("保存方式：hash");
        System.out.println("保存数量："+list.size());
        System.out.println("所用时间："+(end-begin)+"毫秒");
    }


}