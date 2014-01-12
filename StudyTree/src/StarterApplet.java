import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class StarterApplet extends JApplet {

	private static final long serialVersionUID = 1L;

	private Object lock = new Object();
	private Random r = new Random(47);
	private JLabel labelImage;
	private JButton btn;

	public static final String[] fdfdf = { "alan", "allen", "candy", "cath",
			"JFM", "jinayi", "jwohorng", "kelvin", "lisa", "marvin", "vincent",
			"silvia", "xinxin" };

	private volatile boolean stopRunning = true;
	private volatile int luckyNumber = -1;
	private static final long INTERVAL = 1;
	private static final int IMAGE_WIDTH = 444;
	private static final int IMAGE_HEIGHT = 611;
	private final ArrayList<Icon> LIST_IMAGE = new ArrayList<Icon>();
	private String GO_FOR_IT = "go4it.jpg";
	private Icon ICON_Go4_IT = createImageIcon(
			this.getImage(this.getDocumentBase(), GO_FOR_IT), IMAGE_WIDTH,
			IMAGE_HEIGHT);;

	
	private ImageIcon createImageIcon(Image original, int newWidth,
			int newHeight) {
		System.out.println("dddddddddd");
		ImageIcon scaled = new ImageIcon(original.getScaledInstance(newWidth,
				newHeight, Image.SCALE_DEFAULT));
		return scaled;
	}

	private void loadImage(String parentPath) {

		for (String oneFile : fdfdf) {
			if (oneFile.toLowerCase().equalsIgnoreCase(GO_FOR_IT) == false) {
				LIST_IMAGE
						.add(createImageIcon(
								this.getImage(this.getDocumentBase(), oneFile
										+ ".jpg"), IMAGE_WIDTH, IMAGE_HEIGHT));
			}
		}
	}

	private String provideParentPath() {
		return "D:/images";
	}

	public void initComponent() {
		prepareBeforeInitComponent();
		System.out.println("dddd");
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());

		labelImage = new JLabel();
		labelImage.setVerticalAlignment(SwingConstants.CENTER);
		labelImage.setHorizontalAlignment(SwingConstants.CENTER);
		labelImage.setIcon(ICON_Go4_IT);
		container.add(labelImage, BorderLayout.CENTER);

		btn = new JButton("Find Good-Luck-Provider");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleClickEvent();
			}
		});
		container.add(btn, BorderLayout.SOUTH);
		btn.requestFocus();
	}

	private void prepareBeforeInitComponent() {
		stopRunning = true;
		startChangeImageThread();
		loadImage(provideParentPath());
	}

	private void handleClickEvent() {
		synchronized (lock) {
			// currently not running
			// Want to run: if you click, then run
			if (stopRunning == true) {
				// you have clicked. running now........
				btn.setText("Looking for Good-Luck-Provider ..... ");
				stopRunning = false;
				lock.notifyAll();
			}
			// currently running
			// Want to stop running: if you click, then stop running
			else {
				// you have clicked. stopped running. screen not changing now
				btn.setText("Find Good-Luck-Provider");
				stopRunning = true;
				lock.notifyAll();
			}
		}
	}

	private void startChangeImageThread() {
		Thread thread = new Thread("Change-Image") {
			public void run() {
				while (true) {
					synchronized (lock) {
						// currently not running
						// we"ve found the carrier, it is luckyNumber.
						// luckyNumber won"t appear any longer
						while (stopRunning == true) {
							final int finalGoodLuck = luckyNumber;
							if (finalGoodLuck != -1) {
								System.out.println("actually is: "
										+ finalGoodLuck);

								SwingUtilities.invokeLater(new Runnable() {
									@Override
									public void run() {
										System.out.println("stop at: "
												+ finalGoodLuck);
										if (finalGoodLuck == -1) {
											labelImage.setIcon(ICON_Go4_IT);
										} else {
											labelImage.setIcon(LIST_IMAGE
													.get(finalGoodLuck));
										}
										LIST_IMAGE.remove(finalGoodLuck);
										luckyNumber = -1;
									}
								});

							}
							try {
								lock.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

					}

					// Permit to run
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							// when execute invokeLater(), no new
							// luckyNumber will be generated
							luckyNumber = generateLuckyNumber();
							int flickerLuckyNumber = luckyNumber;
							if (flickerLuckyNumber == -1) {
								labelImage.setIcon(ICON_Go4_IT);
							} else {
								labelImage.setIcon(LIST_IMAGE
										.get(flickerLuckyNumber));
							}
						}
					});

					if (stopRunning == false) {
						try {
							Thread.sleep(INTERVAL);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		thread.start();
	}

	private int generateLuckyNumber() {
		// no image left now. return -1. at this time, go4it icon will be
		// displayed
		if (LIST_IMAGE.size() == 0) {
			return -1;
		}
		if (LIST_IMAGE.size() == 1) {
			return 0;
		}
		return r.nextInt(LIST_IMAGE.size());
	}

	public void init() {
		System.out.println("Applet::init()");
	}

	public void start() {
		System.out.println("Applet::start()");
		System.out.println(this.getDocumentBase());
		initComponent();

		btn.requestFocusInWindow();
	}

	public void stop() {
		System.out.println("Applet::stop()");
	}

	public void destroy() {
		System.out.println("Applet::destroy()");
	}
}
