import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Todo extends JFrame{
	JPanel cardPanel;
    CardLayout layout;

	public static void main(String[] args) {
		new Todo();
	}
	
	Todo() {
		super("TODOリスト");
		setLayout(new BorderLayout());
		
		JTextField[] task_list = new JTextField[5];
		JComboBox[] progress_list = new JComboBox[5];
		
		String[][] data = new String[5][2];
		
		// TODOリストのパネル
		// 進捗状況のリスト
		String[] progress_label = {"10%", "20%", "30%", "40%", "50%", "60%", "70%", "80%", "90%", "100%"};
		JPanel panel_list = new JPanel();
		panel_list.setLayout(new GridLayout(5, 2));
		for(int i=0; i < 5; i++) {
			task_list[i] = new JTextField("タスク1");
			panel_list.add(task_list[i]);
			progress_list[i] = new JComboBox(progress_label);
			panel_list.add(progress_list[i]);
		}
		add(panel_list, "Center");
		
		// 新規作成と保存ボタンのパネル
		JPanel panel_menu = new JPanel();
		// 新規作成ボタン
		JButton b_create_list = new JButton("新規作成");
		b_create_list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("新規作成ボタンがクリックされました。");
				// ここに新規作成ボタンが押されたときの処理を記述
						
			}
		});
		panel_menu.add(b_create_list);
		// 保存ボタン
		JButton b_save_list = new JButton("保存");
		b_save_list.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自動生成されたメソッド・スタブ
				System.out.println("保存ボタンがクリックされました。");
				for (int i=0; i<5; i++) {
					data[i][0] = task_list[i].getText();
					data[i][1] = (String)progress_list[i].getSelectedItem();
				}
				for (int i=0; i<5; i++) {
					System.out.print(data[i][0] + ", ");
					System.out.println(data[i][1]);
				}
				// ここに保存ボタンが押された時の処理を記述
				// 変数 data は，2次元配列で [ ["タスク1", "10%"], ["タスク2", "50%"], ... , ["タスク5", "20%"] ] のようになっている
				
				
				
			}
		});
		panel_menu.add(b_save_list);
		add(panel_menu, "North");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		setVisible(true);
		
	}
	
	

}
