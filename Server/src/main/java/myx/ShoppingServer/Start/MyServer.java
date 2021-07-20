package myx.ShoppingServer.Start;

import myx.ShoppingClient.Model.Message;
import myx.ShoppingClient.Model.User;
import myx.ShoppingServer.Database.NewsList;
import myx.ShoppingServer.Database.UserAdd;
import myx.ShoppingServer.Thread.ManageClientThread;
import myx.ShoppingServer.Thread.SerConClientThread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


//此类用于和客户端进行连接
//并接受客户端发来的信息
public class MyServer extends Thread {
    ServerSocket ss;
    ServerSocket ss1;

    public MyServer() {

    }

    public void run() {
        try {

            ss = new ServerSocket(6789);
            System.out.println("  等待连接  ");
            while (true) {
                Socket s = ss.accept();
                Message m = new Message();
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

                User u = (User) ois.readObject();
//4为向消息列表添加用户
                if (u.getMessage().getMesType().equals("4")) {
                    if (NewsList.judge(u)) {
                    } else {
                        NewsList.update(u);
                    }
                    m.setMesType("10");
                    oos.writeObject(m);

                    // 5为向用户返回消息列表
                } else if (u.getMessage().getMesType().equals("5")) {

// 查看标记，当标记等于6，即代表用户信息是用来注册
                } else if (u.getMessage().getMesType().equals("6")) {
                    System.out.println("得到注册请求 id=" + u.getId() + " 密码= " + u.getPassword());
                    if (UserAdd.quary(u.getId())) {
                        System.out.println("注册失败，已有此用户");
                        m.setMesType("2");
                        oos.writeObject(m);
                    } else {
                        UserAdd.add(u);
                        NewsList.add(u);
                        System.out.println("注册成功，添加了此用户 用户id="+u.getId());
                        m.setMesType("10");
                        oos.writeObject(m);
                    }
//查看标记，当标记等于7，即代表用户信息是用来登录
                } else if (u.getMessage().getMesType().equals("7")) {
                    System.out.println("得到登录请求 id=" + u.getId() + " 密码=" + u.getPassword());
                    if (UserAdd.quary(u.getId(), u.getPassword())) {
                        System.out.println("查询成功，此人可以登录");
                        m.setMesType("1");
                        oos.writeObject(m);
                        SerConClientThread scct = new SerConClientThread(s);
                        ManageClientThread.addClientThread(u.getId(), scct);
                        scct.start();
                        // scct.notifyOther(u.getId());
                    } else {
                        System.out.println("查询失败，没有此人信息");
                        m.setMesType("2");
                        oos.writeObject(m);
                        s.close();

                    }

// 查看标记，当标记等于8，即代表用户信息是用来修改个人信息
                } else if (u.getMessage().getMesType().equals("8")) {

                    UserAdd.update(u);
                    m.setMesType("10");
                    oos.writeObject(m);
// 查看标记，当标记等于9，即代表用户信息是用来找回密码
                } else if (u.getMessage().getMesType().equals("9")) {

                    if (UserAdd.quary(u.getId())) {

                        UserAdd.update(u.getId(), u.getPassword());
                        m.setMesType("10");
                        oos.writeObject(m);

                    } else {
                        m.setMesType("2");
                        oos.writeObject(m);
                    }
                }

            } // while 循环中的}

        } catch (Exception e) {
           // e.printStackTrace();
        } finally {

        }

    }

}
