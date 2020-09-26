import org.junit.Test;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestSort {
    public static void main(String[] args) {
        List<SortTestBean> list =  new ArrayList<SortTestBean>();
        list.add(new SortTestBean(10));
        list.add(new SortTestBean(11));
        list.add(new SortTestBean(13));
        list.add(new SortTestBean(3));
        list.add(new SortTestBean(1));
        System.out.println(list);
        Collections.sort(list, new Comparator<SortTestBean>() {
            @Override
            public int compare(SortTestBean o1, SortTestBean o2) {
                return o1.getOrder()>o2.getOrder()?1:-1;
            }
        });
        System.out.println(list);
        String a = "Email";
        String b = "email";
        System.out.println(a.equalsIgnoreCase(b));
    }

    @Test
    public void testEnum(){
        // 验证日期格式为YYYY-MM-DD的正则表达式为
        String regex = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
                String regex2 = "(((0[1-9]|[12][0-9]|3[01])/((0[13578]|1[02]))|((0[1-9]|[12][0-9]|30)/(0[469]|11))|(0[1-9]|[1][0-9]|2[0-8])/(02))/([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3}))|(29/02/(([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00)))";
        String a = "2020-10-10 17:56:40";
        boolean b = a.matches(regex);
        System.out.println(b);
        String header = "data:image/jpg,fileName/ss";
        String []heads = header.split(";");
        System.out.println(heads.length);
        for (int i = 0; i < heads.length; i++) {
            if(heads[i].substring(0,heads[i].indexOf("/")).contains("image")){
                System.out.println("图片后缀名为：."+heads[i].substring(heads[i].indexOf("/")+1));
            }else{
                System.out.println("文件后缀名为：."+heads[i].substring(heads[i].indexOf("/")+1));
            }
        }
    }

    public boolean isInclude(Class<? extends Enum<?> >clazz,int code){
        List enumList = getEnumList(clazz);
        for(int i=0;i<enumList.size();i++){
            Object en = enumList.get(i);
            Class<?> enClass = en.getClass();
            //通过以上步骤就已经成功获取枚举的类型与枚举对像，接下来只需要通过反射调用对应的方法即可
            try{
                Method method = enClass.getMethod("getCode");
                Object result = method.invoke(en,null);
                if(Integer.parseInt(result.toString())==code){
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List getEnumList(Class enumClazz){
        List list = new ArrayList();
        Object [] enums = enumClazz.getEnumConstants();
        for(int i=0;i<enums.length;i++){
            list.add(enums[i]);
        }
        return list;
    }


}
