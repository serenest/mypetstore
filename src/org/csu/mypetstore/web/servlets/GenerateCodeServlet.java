package org.csu.mypetstore.web.servlets;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;



public class GenerateCodeServlet extends HttpServlet{
    private static final int lineWidth = 1;

    private static final int width = 80;

    private static final int height = 50;

    private Random random;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        random = new Random();

        resp.setHeader("Pragma","no-cache");
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Expires","0");

        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D img = (Graphics2D) image.getGraphics();

        img.setColor(getRandColor(230,255));
        img.fillRect(0,0,width,height);

        img.setColor(getRandColor(100, 180));
        img.drawRect(0, 0, width - 1, height - 1);


        char[] rands = new char[4];
        generateRands(rands);   //生成随机验证码

        drawRands(img,rands);       //把验证码画在图片上

        drawInterLine(img);     //生成干扰线

        session.setAttribute("verifyCode", String.valueOf(rands).toLowerCase());
        // 输出图象到页面
        try {
            ImageIO.write(image, "JPG", resp.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private void generateRands(char[] rands){
        int m;
        boolean n;
        char ch;

        for(int i=0; i<4; i++)
        {
            m = random.nextInt(36);
            n = random.nextBoolean();

            if(m<=25)
            {
                ch = (char) ('a' + m);

                if(n)
                    ch = (char)('A'+(ch-'a'));
            }
            else
                ch = (char)('0'+(m-26));

            rands[i] = ch;
        }
    }

    private Color getRandColor(int fc, int bc){
        if(fc>255)
            fc = 255;
        if(bc>255)
            bc = 255;

        int r = fc+random.nextInt(bc-fc);
        int g = fc+random.nextInt(bc-fc);
        int b = fc+random.nextInt(bc-fc);

        return new Color(r,g,b);
    }

    private void drawRands(Graphics2D img, char[] rands){
        for(int i = 0; i < rands.length; i++)
        {
            img.setColor(getRandColor(50,200));
            Font font = new Font("Microsoft YaHei", Font.ITALIC, 22);
            img.setFont(font);

            int degree = random.nextInt() % 30;
            int x = (i*15)+10;

            img.rotate(degree * Math.PI / 360, x,15);
            img.drawString(rands[i] + "", x, 30);
        }
    }

    private void drawInterLine(Graphics img){

        for(int i=0; i<200; i++)
        {
            final int x = random.nextInt(width - lineWidth - 1) + 1; // 保证画在边框之内
            final int y = random.nextInt(height - lineWidth - 1) + 1;
            final int xl = random.nextInt(lineWidth);
            final int yl = random.nextInt(lineWidth);
            img.drawLine(x, y, x + xl, y + yl);
        }
    }
}
