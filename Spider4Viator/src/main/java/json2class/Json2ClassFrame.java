package json2class;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Json2ClassFrame extends JFrame {

	private JTextArea textField;
	private JButton btnGenerate;
	private JButton btnChoose;
	private JTextArea textArea;
	private JFileChooser fc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Json2ClassFrame frame = new Json2ClassFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Json2ClassFrame() {
		setTitle("Json2Class");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generate(e);
			}
		});
		contentPane.add(btnGenerate, BorderLayout.SOUTH);
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generate(e);
			}
		});
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.setFont(new Font("DIALOG", Font.PLAIN, 20));
		scrollPane.setViewportView(textArea);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		btnChoose = new JButton("...");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choose(e);
			}
		});
		panel.add(btnChoose, BorderLayout.EAST);

		textField = new JTextArea();
		textField.setFont(new Font("DIALOG", Font.PLAIN, 20));
		panel.add(textField);
		textField.setColumns(10);
	}

	private void generate(ActionEvent e) {
		String jsonFullPath = textField.getText();
		if (!new File(jsonFullPath).isFile()) {
			textArea.setText("选个文件吧");
			return;
		}
		try {
			String javaFullPath = Json2Class.file2Class(jsonFullPath);
			StringBuilder sb = new StringBuilder();
			sb.append("成功" + Json2Class.ENTER);
			sb.append("生成文件：" + javaFullPath + Json2Class.ENTER);
			textArea.setText(sb.toString());
		} catch (Exception e1) {
			StringBuilder sb = new StringBuilder();
			sb.append("失败" + Json2Class.ENTER);
			sb.append("Json格式不对，或者，我不对" + Json2Class.ENTER);
			textArea.setText(sb.toString());
		}
	}

	private void choose(ActionEvent e) {
		if (fc == null) {
			fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		}
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			textField.setText(fc.getSelectedFile().getAbsolutePath());
		}
	}
}
