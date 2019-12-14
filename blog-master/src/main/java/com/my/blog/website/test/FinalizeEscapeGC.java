package com.my.blog.website.test;/*
 * @Author chengpunan
 * @Description //TODO $end$
 * @Date $time$ $date$
 * @Param $param$
 * @return $return$
 **/

public class FinalizeEscapeGC {

    private static  FinalizeEscapeGC save_hook=null;

    /**
     * 是否生存
     */
    private void isAlive(){
        System.out.println("yes,i am alive;");
    }

    @Override
    protected void finalize() throws Throwable {



        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.save_hook=this;
    }

    public static void main(String[] args) throws  Throwable {
        save_hook=new FinalizeEscapeGC();
        save_hook=null;
        System.gc();
        Thread.sleep(500);
        if(save_hook!=null){
            save_hook.isAlive();
        }else{
            System.out.println("i am dead;");
        }


        save_hook=null;
        System.gc();
        Thread.sleep(500);
        if(save_hook!=null){
            save_hook.isAlive();
        }else{
            System.out.println("i am dead;");
        }

    }
}
