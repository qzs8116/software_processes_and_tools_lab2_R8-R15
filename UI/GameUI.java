package UI;

import Board.Board;
import Start.Control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameUI{

    private Control c;

    private final JFrame frame = new JFrame();
    private final JButton[][] jb;
    private final JButton up = new JButton();
    private final JButton down = new JButton();
    private final JButton left = new JButton();
    private final JButton right = new JButton();

    private final JButton again = new JButton();
    private final JButton remove = new JButton();

    private final JLabel score = new JLabel();
    private final JLabel coin = new JLabel();
    private final JLabel maxScore = new JLabel();

    public GameUI(int num, Control aC){
        jb = new JButton[num][num];
        for(int i = 0; i < num; i++){
            for(int j = 0; j < num; j++){
                jb[i][j] = new JButton();
                jb[i][j].setEnabled(false);
            }
        }
        c = aC;
    }

    /**
     * 显示界面
     * @param num
     * @param goldCoin
     * @param max_score
     */
    public void showButton(int num, int goldCoin, int max_score){
        for (int i = 0; i < jb.length; i++){
            for (int j = 0; j < jb.length; j++){
                jb[i][j].setBounds(100*i+2, 100*(j+1), 100, 100);
                frame.add(jb[i][j]);
            }
        }
        score.setBounds(250, 50, 200, 20);
        score.setText("分数：" + String.valueOf(num));
        frame.add(score);

        coin.setBounds(150,50, 80, 20);
        coin.setText("金币：" + String.valueOf(goldCoin));
        frame.add(coin);

        maxScore.setBounds(225, 10, 200, 20);
        maxScore.setText("最高得分：" + String.valueOf(max_score));
        frame.add(maxScore);

        up.setBounds(170, 525, 60, 60);
        down.setBounds(170, 665, 60, 60);
        left.setBounds(100, 595, 60, 60);
        right.setBounds(240, 595, 60, 60);
        again.setBounds(30,10, 70,30);
        remove.setBounds(30,50, 70, 30);

        up.setText("上");
        down.setText("下");
        left.setText("左");
        right.setText("右");
        again.setText("重开");
        remove.setText("消除");

        frame.add(up);
        frame.add(down);
        frame.add(left);
        frame.add(right);
        frame.add(again);
        frame.add(remove);

        up.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.upControl();
            }
        });

        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.downControl();
            }
        });

        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.lfetControl();
            }
        });

        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.rightControl();
            }
        });

        again.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.restart();
                frame.dispose();
            }
        });

        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirm("选择删除的方快！");
            }
        });

        frame.setSize(420, 780);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

    }

    /**
     * 确认消除多少数字的方块
     * @param message
     */
    public void confirm(String message){
        JFrame frame = new JFrame();
        JLabel label = new JLabel(message);
        JButton b1 = new JButton("2");
        JButton b2 = new JButton("4");
        JButton b3 = new JButton("取消");
        JScrollPane scrollPane = new JScrollPane(label);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(b1, BorderLayout.BEFORE_LINE_BEGINS);
        frame.getContentPane().add(b2, BorderLayout.AFTER_LINE_ENDS);
        frame.getContentPane().add(b3, BorderLayout.PAGE_END);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = c.removeSquare(2);
                if(i == 0){
                    Prompt("无此类方块！");
                }
                else if (i == 1){
                    Prompt("金币不足！");
                }
                else if(i == 2){
                    Prompt("消除成功！");
                }
                frame.dispose();
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = c.removeSquare(4);
                if(i == 0){
                    Prompt("无此类方块！");
                }
                else if (i == 1){
                    Prompt("金币不足！");
                }
                else if(i == 2){
                    Prompt("消除成功！");
                }
                frame.dispose();
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }

    /**
     * 游戏结束是否重新开始
     * @param message
     */
    public void showMessage(String message) {
        JFrame frame = new JFrame();
        JLabel label = new JLabel(message);
        JButton b = new JButton("确定");
        JScrollPane scrollPane = new JScrollPane(label);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(b, BorderLayout.AFTER_LAST_LINE);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promptRestart("是否重新开始！");
                frame.dispose();
            }
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }

    /**
     * 提示是否重新开始
     * @param message
     */
    public void promptRestart(String message) {
        JFrame frame = new JFrame();
        JLabel label = new JLabel(message);
        JButton b1 = new JButton("确定");
        JButton b2 = new JButton("取消");
        JScrollPane scrollPane = new JScrollPane(label);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(b1, BorderLayout.BEFORE_LINE_BEGINS);
        frame.getContentPane().add(b2, BorderLayout.AFTER_LINE_ENDS);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.restart();
                frame.dispose();
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }


    /**
     * 提示信息
     * @param message
     */
    public void Prompt(String message) {
        JFrame frame = new JFrame();
        JLabel label = new JLabel(message);
        JButton b = new JButton("确定");
        JScrollPane scrollPane = new JScrollPane(label);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(b,BorderLayout.AFTER_LAST_LINE);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }

    public void changScore(int num){
        score.setText("分数：" + String.valueOf(num));
    }

    public void changCoin(int num){
        coin.setText("金币：" + String.valueOf(num));
    }

    public void changMaxScore(int num){
        maxScore.setText("最高得分：" + String.valueOf(num));
    }

    /**
     * 更新方块颜色与数字
     * @param board
     */
    public void changeButton(Board board){
        for (int i = 0; i <jb.length; i++){
            for (int j = 0; j < jb.length; j++){
                jb[i][j].setText("");
                jb[i][j].setBackground(new Color(246, 213, 133));
                if(board.getPosition(i, jb.length-j-1).getSquare() != null) {
                    switch (board.getPosition(i, jb.length-j-1).getSquare().getNum()){
                        case 2: jb[i][j].setText("2");
                                jb[i][j].setFont(new Font("宋体", Font.BOLD, 40));
                                jb[i][j].setBackground(new Color(246, 241, 241));
                                jb[i][j].setForeground(new Color(9, 1, 1, 248));
                                break;

                        case 4: jb[i][j].setText("4");
                                jb[i][j].setFont(new Font("宋体", Font.BOLD, 40));
                                jb[i][j].setBackground(new Color(243, 194, 8));
                                jb[i][j].setForeground(new Color(12, 0, 0));
                                break;

                        case 8: jb[i][j].setText("8");
                                jb[i][j].setFont(new Font("宋体", Font.BOLD, 40));
                                jb[i][j].setBackground(new Color(245, 153, 5));
                                jb[i][j].setForeground(new Color(255, 254, 254));
                                break;

                        case 16: jb[i][j].setText("16");
                                 jb[i][j].setFont(new Font("宋体", Font.BOLD, 40));
                                 jb[i][j].setBackground(new Color(239, 97, 30));
                                 jb[i][j].setForeground(new Color(255, 254, 254));
                                 break;

                        case 32: jb[i][j].setText("32");
                                 jb[i][j].setFont(new Font("宋体", Font.BOLD, 40));
                                 jb[i][j].setBackground(new Color(252, 3, 3));
                                 jb[i][j].setForeground(new Color(255, 254, 254));
                                 break;

                        case 64: jb[i][j].setText("64");
                                 jb[i][j].setFont(new Font("宋体", Font.BOLD, 40));
                                 jb[i][j].setBackground(new Color(168, 2, 24));
                                 jb[i][j].setForeground(new Color(255, 254, 254));
                                 break;

                        case 128: jb[i][j].setText("128");
                                  jb[i][j].setFont(new Font("宋体", Font.BOLD, 40));
                                  jb[i][j].setBackground(Color.yellow);
                                  jb[i][j].setForeground(new Color(255, 254, 254));
                                  break;

                        case 256: jb[i][j].setText("256");
                                  jb[i][j].setFont(new Font("宋体", Font.BOLD, 40));
                                  jb[i][j].setBackground(new Color(191, 138, 4));
                                  jb[i][j].setForeground(new Color(255, 254, 254));
                                  break;

                        case 512: jb[i][j].setText("512");
                                  jb[i][j].setFont(new Font("宋体", Font.BOLD, 40));
                                  jb[i][j].setBackground(new Color(172, 125, 6));
                                  jb[i][j].setForeground(new Color(255, 254, 254));
                                  break;

                        case 1024: jb[i][j].setText("1024");
                                   jb[i][j].setFont(new Font("宋体", Font.BOLD, 40));
                                   jb[i][j].setBackground(new Color(125, 92, 9));
                                   jb[i][j].setForeground(new Color(255, 254, 254));
                                   break;

                        case 2048: jb[i][j].setText("2048");
                                   jb[i][j].setFont(new Font("宋体", Font.BOLD, 40));
                                   jb[i][j].setBackground(new Color(95, 69, 3));
                                   jb[i][j].setForeground(new Color(255, 254, 254));
                                   break;

                        case 4096: jb[i][j].setText("4096");
                                   jb[i][j].setFont(new Font("宋体", Font.BOLD, 40));
                                   jb[i][j].setBackground(new Color(14, 10, 0));
                                   jb[i][j].setForeground(new Color(255, 254, 254));
                                   break;
                    }
                }
            }
        }
    }
}
