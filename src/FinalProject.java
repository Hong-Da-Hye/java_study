	import javax.swing.*;
	import javax.swing.border.Border;
	import javax.swing.event.ListSelectionEvent;
	import javax.swing.event.ListSelectionListener;
	import javax.swing.filechooser.*;
	import java.awt.event.*;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.ObjectInputStream;
	import java.io.ObjectOutputStream;
	import java.io.Serializable;
	import java.util.Calendar;
	import java.util.HashMap;
	import java.util.Vector;
	import java.awt.*; 
			
			class Item implements Serializable{
				public String title, author, date, imagefi, star, summary, revie;
		              int dit, n, star2;			
				public void set(String title, String author, String imagefi, String summary, String revie, String date, String star, int dit, int n, int star2){
					this.title = title;
					this.author = author;
					this.imagefi = imagefi;
					this.summary = summary;
					this.revie = revie;
					this.date = date;
					this.star = star;
					this.dit = dit;
					this.n = n;
					this.star2 = star2;				
				}	    
			    // ��ȭ, å ���� ��
				public int getDit() {
					return dit;
				}			
				public int getdateIndex() {
					return n;
				}			
				public int getscor() {
					return star2;
				}	
				public String [] getAuthor() {
					String s[] = {title, author, date, imagefi, star, summary, revie};
					return s;
				}			
				public void setAuthor(String author) {
					this.author = author;
				} 
				//�ʿ��� ������ getter�Լ�   
		        			
				@Override
				public String toString() {
					return title;
				}  // toString() �������̵�
						   
			}
			
			// Item Ŭ������ ����ϴ� subclass Movie Ŭ���� �ۼ�
			class Movie extends Item implements Serializable{
				String sort, age, acto;
				int agen, sortn;
				public void setMovie(String acto, String sort, String age, int agen, int sortn) {
					this.acto = acto;
					this.sort = sort;
					this.age = age;
					this.agen = agen;
					this.sortn = sortn;
				}			
				public String [] getele() {
					String s[] = {sort, age, acto};			
					return s;
				}			
				public int getageIndex() {
					return agen;
				}
				public int getsortIndex() {
					return sortn;
				}			
			}
			// Item Ŭ������ ����ϴ� subclass Book Ŭ���� �ۼ�
			class Book extends Item implements Serializable{
				String publi;
			   public void setMovie(String publi) {
				this.publi = publi;
				}			
				public String getPubli() {
					return publi;
				}
				
			}
			
			// ItemCollections Ŭ���� ����
			class ItemCollections{
				Vector<Item> v = new Vector<Item>();
				
				public ItemCollections(Vector<Item> v) {
					this.v = v;
				}
				public void Iadd(Item m) {
					v.add(m);
				} // Item v�� �߰��ϴ� �Լ�
				
				public void Idelete(Item search){
					v.remove(search);
				}     // Item v�� �����ϴ� �Լ�
				public void Iedit(int n, Item edit) {
					v.set(n, edit);			
				}    // Item v�� �����ϴ� �Լ�				
				
			}
			
			class MyModalDialog extends JDialog { 
				JRadioButton radi[] = new JRadioButton[2];
				JTextField [] Text = new JTextField[6];
				JTextArea [] text = new JTextArea [4];
				JLabel [] textm = new JLabel[10];
				JLabel [] textb = new JLabel[8];
				JComboBox<String> sortc = new JComboBox<String>();
				JComboBox<String> agec = new JComboBox<String>();
				JComboBox<Integer> yearm = new JComboBox<Integer>();
				JComboBox<Integer> yearb = new JComboBox<Integer>();
				JSlider scom, scom2;
				String radiot [] = {"Movie", "Book"};
				String textms [] = {"����", "����", "���", "�帣", "���", "�����⵵", "������", "����", "�ٰŸ�", "������"}; 
				String textbs [] = {"����", "����", "���ǻ�", "���ǳ⵵", "å�̹���", "����", "å�Ұ�", "������"}; 
				String sort []= {"���", "�ڹ̵�", "�̽��͸�, ������", "����", "�ִϸ��̼�", "�׼�"};
				String age [] = {"��ü", "12�� �̻�", "15�� �̻�", "û�ҳ� ���� �Ұ�"};
				String postIfn, postIbfn;
				JTextField postIt, postbIt;
				
				// ��� ���̾�α� �ۼ�
				public MyModalDialog(JFrame frame, String title) { 
					super(frame,title, true); // ��� ���̾�α׷� ���� 
					setLayout(new BorderLayout()); 
					
							
					JPanel bookt = new JPanel();
					JPanel RadioB = new JPanel();
					ButtonGroup g = new ButtonGroup();
					Border resultBorder = BorderFactory.createTitledBorder("��ȭ ����");
					Border resultBorder2 = BorderFactory.createTitledBorder("å ����");
					
					GridLayout grid2 = new GridLayout(0, 2);
				    grid2.setVgap(3);
				    
				    // ���� ��ư�� Movie�� ���� UI �ۼ�
				    JPanel moviet = new JPanel();
				    JPanel movieCen = new JPanel();
				    JPanel moviebott = new JPanel();
					moviet.setLayout(null);
					movieCen.setLayout(null);
					moviebott.setLayout(null);
					// ��ġ ������ ����
					
					JPanel lef = new JPanel();
					JPanel rig = new JPanel();
				    GridLayout grid = new GridLayout(0, 1);
				    grid.setVgap(3);
				    lef.setLayout(grid);		   
				    //��ȭ �� ��ü ���� �� �ҳο� �߰�
				    for(int i = 0; i < 8; i++){
				    	textm[i] = new JLabel(textms[i]);
				    	lef.add(textm[i]);
				    } 
				    
				    moviet.setBorder(resultBorder);
				    rig.setLayout(grid);
				    
				    // ������ ��ҵ��� ��ü ���� ����
				    for(int i = 0; i < 3; i++) {
						Text[i] = new JTextField(25);
						rig.add(Text[i]);
					}
				    // ���� �帣, ����, �⵵�� �ش��ϴ� �޺��ڽ� ��� �߰�
					for(int i = 0; i < sort.length; i++) 
						sortc.addItem(sort[i]);
			       
			        for(int i =0; i < age.length; i++) 
						agec.addItem(age[i]);
			     
			        for(int i = 20; i >= 0; i--) 
						yearm.addItem(2000 + i);
			       
			        // ������ �̹���
			        JPanel postI = new JPanel();
			        postIt = new JTextField(15);
			        // �ҷ����� ��ư�� ������ ������ �̹����� ���� �ּ� ����
			        JButton pimagf = new JButton("�ҷ�����");
			        // �׼� ������ �ۼ�
			        pimagf.addActionListener(new ActionListener() {
			        	private JFileChooser chooser = new JFileChooser(); 
			        	 public void actionPerformed(ActionEvent e) { 	     		
			     		
			     				int f = chooser.showOpenDialog(null); 
			     				// ���� ���̾�α� ���
			     				if(f != JFileChooser.APPROVE_OPTION) { 
			     					JOptionPane.showMessageDialog(null, 
			     					"������  ��������  �ʾҽ��ϴ�", "���", 
			     					JOptionPane.WARNING_MESSAGE); 
			     					return; 
			     					} 
			     				if(f == JFileChooser.APPROVE_OPTION) { 
			     					String patName = chooser.getSelectedFile().getPath();
			     					postIt.setText(patName);
			     					
			     				} // ����ڰ� ������ ���� �̸� �˾Ƴ� �ؽ�Ʈ �ʵ� ���� ����
			     			}	     	      
			        });
			        // movie�� �ش��ϴ� JSlider ���� �� ����
			        scom = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
			        scom.setPaintLabels(true);
			        scom.setPaintTicks(true);
			        scom.setPaintTrack(true);
			        scom.setMajorTickSpacing(1);
			        scom.setMinorTickSpacing(1);
			        
			        postI.add(postIt); postI.add(pimagf);
			        rig.add(sortc);rig.add(agec); rig.add(yearm);
			        rig.add(postI); rig.add(scom);
			        //�� ��� rig�� �߰�
			       
			        lef.setLocation(40,20);	lef.setSize(50, 400);		        
					rig.setLocation(120,20);  rig.setSize(300, 405);
					// lef, rig �ҳ� ũ��, ��ġ ����
				    movieCen.add(lef);  movieCen.add(rig);
				    
				    movieCen.setLocation(10,20);
					movieCen.setSize(500, 430);			    		
				    moviet.add(movieCen);	    
				    // �ٰŸ��� ������ �ش��ϴ� TextArea �߰�			    
				    JPanel lef1 = new JPanel();
				    JPanel rig1 = new JPanel();
				    lef1.setLayout(grid);
				    rig1.setLayout(grid);
				    textm[8] = new JLabel(textms[8]); 
				    textm[9] = new JLabel(textms[9]);
				    lef1.add(textm[8]); lef1.add(textm[9]);
			        text[0] = new JTextArea("", 5, 20);
			        text[1] = new JTextArea("", 5, 20);
			        rig1.add(new JScrollPane(text[0]));  rig1.add(new JScrollPane(text[1]));
			        lef1.setLocation(40, 50);  lef1.setSize(50, 220);
			        rig1.setLocation(100,50);  rig1.setSize(340, 230);
			        moviebott.setLocation(10,400);
					moviebott.setSize(500, 280);
			    	moviebott.add(lef1);
			    	moviebott.add(rig1);
			    	moviet.add(moviebott);
			    	
			    	
			    	// �޺��ڽ��� Book�� ���� UI �ۼ�
				    JPanel bookCen = new JPanel();
				    JPanel bookbott = new JPanel();
					
					bookCen.setLayout(null);
					bookbott.setLayout(null);
					// ��ġ ������ ����
					JPanel lefb = new JPanel();
					JPanel rigb = new JPanel();
				    lefb.setLayout(grid);
			
				    for(int i = 0; i < 6; i++){
				    	textb[i] = new JLabel(textbs[i]);
				    	lefb.add(textb[i]);
				    }
				    // �� ��ü ���� �� lefb �ҳ��� �߰�    
				    rigb.setLayout(grid);
				    
				    for(int i = 3; i < 6; i++) {
						Text[i] = new JTextField(25);
						rigb.add(Text[i]);
					} // ����, ����, ���ǻ翡 �ش��ϴ� �ؽ�Ʈ �ʵ� ��ü ������ rigb �ҳο� �߰�
				
			        for(int i = 20; i >= 0; i--) 
						yearb.addItem(2000 + i);
			        // �⵵�� �ش��ϴ� �޺��ڽ� ��� �߰� 
			        scom2 = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
			        scom2.setPaintLabels(true);
			        scom2.setPaintTicks(true);
			        scom2.setPaintTrack(true);
			        scom2.setMajorTickSpacing(1);
			        scom2.setMinorTickSpacing(1);
			        // ������ �ش��ϴ� JSlider ��ü ���� �� ���� 
			        
			        // ������ �̹��� 
			        JPanel postbI = new JPanel();
			        postbIt = new JTextField(15);
			        // ������ ���� �̸� �������� 
			        JButton pimagbf = new JButton("�ҷ�����");
			        pimagbf.addActionListener(new ActionListener() {
			        	private JFileChooser chooser = new JFileChooser(); 
			        	 public void actionPerformed(ActionEvent e) { 	     		
			     		
			     				int f = chooser.showOpenDialog(null); 
			     				// �ҷ����� ���̾�α� ���
			     				if(f != JFileChooser.APPROVE_OPTION) { 
			     					JOptionPane.showMessageDialog(null, 
			     					"������  ��������  �ʾҽ��ϴ�", "���", 
			     					JOptionPane.WARNING_MESSAGE); 
			     					return; 
			     					} 
			     				if(f == JFileChooser.APPROVE_OPTION) { 
			     					String patName = chooser.getSelectedFile().getPath();
			     					postbIt.setText(patName);
			     					
			     				} // ����ڰ� ������ ���� �̸� �˾Ƴ� �ؽ�Ʈ �ʵ� ���� ����
			     			}
			     	      
			        });
			        					
			        // �ҳο� ���ݱ����� ��� �߰� �� ��ġ, ������ ����
			        postbI.add(postbIt);  postbI.add(pimagbf);
			        rigb.add(yearb); rigb.add(postbI);  rigb.add(scom2);		       
			        lefb.setLocation(40,20); lefb.setSize(50, 320);		        
					rigb.setLocation(120,20); rigb.setSize(300, 330);
					
				    bookCen.add(lefb);  bookCen.add(rigb);			    
				    bookCen.setLocation(10,20);  bookCen.setSize(500, 340);			   			
					bookt.setLayout(null); bookt.setBorder(resultBorder2); bookt.add(bookCen);
				    // �ٰŸ��� ������ �ش��ϴ� TextArea �߰�
				    JPanel lefb1 = new JPanel();
				    JPanel rigb1 = new JPanel();
				    lefb1.setLayout(grid);
				    rigb1.setLayout(grid);
				    textb[4] = new JLabel(textbs[6]);
				    textb[5] = new JLabel(textbs[7]);
				    lefb1.add(textb[4]);
				    lefb1.add(textb[5]);
			        text[2] = new JTextArea("", 5, 20);	       
			        text[3] = new JTextArea("", 5, 20);
			        rigb1.add(new JScrollPane(text[2]));
				    rigb1.add(new JScrollPane(text[3]));
			        lefb1.setLocation(40, 50); lefb1.setSize(50, 220);
			        rigb1.setLocation(100,50); rigb1.setSize(340, 230);
			        bookbott.setLocation(10,330); bookbott.setSize(500, 280);
			    	bookbott.add(lefb1);  bookbott.add(rigb1);
			    	bookt.add(bookbott);
			    		       
			        // ���� ��ư ���� �� �߰�, ������ �߰�
				    for(int i = 0; i < 2; i++) {
						radi[i] = new JRadioButton(radiot[i]);
						g.add(radi[i]);
						RadioB.add(radi[i]);
						int countm = 0;
						int countb = 0;
						//���� ��ư�� ���� �� �ش� ���� ��ư�� �ش��ϴ� Jpanel ���̵��� �ϴ� �׼� ������ 
						radi[i].addItemListener(new ItemListener() {
							@Override
							
							public void itemStateChanged(ItemEvent e) {
								 // ���� ��ư�� ���� ���� �� ���
								if(e.getStateChange() == ItemEvent.DESELECTED) {								
									JRadioButton k = (JRadioButton)e.getSource();
									String s = k.getText();
									if(s.equals("Movie")) // ���� ��ư�� ��ȭ �� ��� �ش� �ҳ� ����
										moviet.setVisible(false);							    
									if(s.equals("Book")) // ���� ��ư��  å �� ��� �ش� �ҳ� ����
									    bookt.setVisible(false);									
									return;
								}
								if(radi[0].isSelected()) { // Movie ���̿� ��ư�� ������ �ش� �ǳ� ���̵���
									if(countm == 0)
									add(moviet,BorderLayout.CENTER);
									moviet.setVisible(true);
									
								}
								else {
									if(countb == 0)  // Book ���̿� ��ư�� ������ �ش� �ǳ� ���̵���
									add(bookt,BorderLayout.CENTER);
									bookt.setVisible(true);
									
								}
							}
							
						});
					}
				    radi[0].setSelected(true); // ù��° ���� ��ư ���� ���·� ����
					// ���� ��ư�� ���� ��Ʈ �߰�
				    add(RadioB,BorderLayout.NORTH);
					JPanel p3 = new JPanel();
					JButton okb = new JButton("OK");  // ���� ��ư �߰�
					p3.add(okb);
					add(p3, BorderLayout.SOUTH);
				
				setSize(500, 800); 
				// OK ��ư�� ���� ��� �׼� ������
				okb.addActionListener(new ActionListener() { 
					public void actionPerformed(ActionEvent e) { 
			             setVisible(false);
			            // ��� ���̾�α� �Ⱥ��̵��� ����
				} 				
				}); // JDialog�� ��ӹ޾� ���̾�α� �����			
				} 
				// Movie�� �ؽ�Ʈ �ʵ�� �ؽ�Ʈ �ʵ��� ������ �� String �迭 ����
				public String[] getInputMovie(){  
					String [] mt = new String [6];
					for(int i = 0; i < 3; i++) {
					mt[i] = Text[i].getText();
					Text[i].setText("");
					}
					mt[3]= postIt.getText();
					postIt.setText("");
					mt[4] = text[0].getText();
					text[0].setText("");
					mt[5] = text[1].getText();
					text[1].setText("");
					return mt;
				}
				// �帣 ���� ����
				public String getInputSort() {
					String sort = sortc.getSelectedItem().toString();				
					 return sort;				 
				} 
				// ���� ��� ����
				public String getInputage() {
					String age = agec.getSelectedItem().toString();				
					 return age;
				} 
				//��ȭ �����⵵ ����
				public String getInputdate() {
					String year = yearm.getSelectedItem().toString();				
					 return year;
					 	} 
				//��ȭ ���� ���ڿ��� �ٲ� ����
				public String getInputscor() {
					 return Integer.toString(scom.getValue());
					 	} 
				//��ȭ ���� ����
				public int getInputscorm2() {
					 return scom.getValue();
					 	} 
				// å ���� ���ڿ��� �ٲ� ����
				public String getInputscor2() {
				 	 return Integer.toString(scom2.getValue());
					 	} 
				// å ���� ����
				public int getInputscorb2() {
					 return scom2.getValue();
					 	} 
				// �帣�� �ε��� ����
				public int getsortIndex() {		
					return sortc.getSelectedIndex();
				}
				//��ȭ �����⵵�� �ش� �ε��� ����
				public int getyearmIndex() {		
					return yearm.getSelectedIndex();
				}
				// ��ȭ ��� �ε��� ����
				public int getagecIndex() {		
					return agec.getSelectedIndex();
				}
				// å �����⵵ �ش� �ε��� ����
				public int getyearbIndex() {		
					return yearb.getSelectedIndex();
				}
				// Book�� �ؽ�Ʈ �ʵ�� �ؽ�Ʈ �ʵ��� ������ �� String �迭 ����
				public String[] getInputBook(){
					String [] bt = new String [6];
					for(int i = 0; i < 3; i++) {
					bt[i] = Text[i + 3].getText();
					Text[i + 3].setText("");
					}
					bt[3]= postbIt.getText();
					postIt.setText("");
					bt[4] = text[2].getText();
					text[2].setText("");
					bt[5] = text[3].getText();
					text[3].setText("");
					return bt;
				}
				// ��ȭ �����⵵ ����
				public String getInputbdate() {
					 return yearb.getSelectedItem().toString();
				} 		
				//���õ� ���� ��ư�� �ش��ϴ� ���ڿ� ���� 
				public String getInputbRadioB() {				
				if(radi[0].isSelected()) {
					String r = "Movie";
					return r;
				}
				if(radi[1].isSelected()) {
					String b = "Book";
				      return b;			
				}
				else {return "C";}
				}
				
				public void setMovie(Item item) { // ��ȭ ���� ��������
					Movie m = (Movie) item;
					String moviearray [] = m.getAuthor();
					String m2 [] = m.getele();			
					int scor = m.getscor();
					radi[0].setSelected(true);
					 			
					Text[0].setText(moviearray[0]);
					Text[1].setText(moviearray[1]);
					Text[2].setText(m2[2]);
					postIt.setText(moviearray[3]);
					sortc.setSelectedIndex(m.getsortIndex());
					agec.setSelectedIndex(m.getageIndex());
					yearm.setSelectedIndex(m.getdateIndex());
					scom.setValue(scor);
					text[0].setText(moviearray[5]);
					text[1].setText(moviearray[6]);							
				}
				
				public void setbook(Item item) { // å ���� ��������
					Book b = (Book) item;
					String bookarray [] = b.getAuthor();
					String b2 = b.getPubli();			
					int yearindex = b.getdateIndex();
					int scor = b.getscor();
					radi[1].setSelected(true);				
					Text[3].setText(bookarray[0]);
					Text[4].setText(bookarray[1]);
					Text[5].setText(b2);
					postbIt.setText(bookarray[3]);			
					yearb.setSelectedIndex(b.getdateIndex());
					scom2.setValue(scor);
					text[2].setText(bookarray[5]);
					text[3].setText(bookarray[6]);										
				}
				
			}
				
				public class FinalProject extends JFrame {
				
				    JTextField tf[] = new JTextField[4];	
				    String tp[] = {"��ü", "��ȭ", "����", "�˻�"};
				    JTextArea mi, rev;
				    JPanel text, text2, p3;
				    int dist = 0;
				    int n;
					ImageIcon ico = new ImageIcon("C:\\Users\\user\\Desktop\\�ڹ� ����\\LineFinalProject\\images\\�̹�����.jpg");
					ImageIcon ico2;
					Image imgb = ico.getImage();
				    Image changeImageb = imgb.getScaledInstance(230, 275, Image.SCALE_SMOOTH);
				    // �̹��� ������ ���� 
				    ImageIcon changeIconb = new ImageIcon(changeImageb);
				    // �̹��� �����ܿ� ������ ��ü 				   				
				    
				    JLabel imageLabel = new JLabel();			
				    GridLayout grid, grid2;
				     Vector<Item> movie = new Vector<Item>();
				     Vector<Item> book = new Vector<Item>();
				     Vector<Item> total = new Vector<Item>();			    	    
				     Vector<String> sear = new Vector<String>();
				     
				     ItemCollections movievec, bookvec;
				     JList<Item> totalL = new JList<Item>(total);
				     JList<Item> movieL = new JList<Item>(movie);
				     JList<Item> bookL = new JList<Item>(book);
				     
				     JPanel search = new JPanel();
				     JList<Item> searL = new JList<Item>();
				     
				    JLabel infor[] = new JLabel[7];
				    JLabel infor2[] = new JLabel[7];
				    JLabel inforb[] = new JLabel[7];
				    JLabel inforb2[] = new JLabel[7];
				    String infors[] = {"����", "����", "���", "�帣", "���", "�����⵵", "����"};
				    String inforsb[] = {"����", "����", "���ǻ�", "���ǳ⵵", "����"};
				    
					public FinalProject() throws IOException {
						setTitle("JAVA 05 1812545 ȫ����");
						setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						Container c = getContentPane(); 
						c.setLayout(new BorderLayout());	
						 					 
						creatMenu();  // �޴��� ����� �Լ� 
						
						JTabbedPane pane = ResultTabbedPane();
						
						JPanel east = new JPanel();
						JPanel button = new JPanel();
						east.setLayout(new BorderLayout(0,1));
						east.add(pane, BorderLayout.CENTER);					
					    // JList�鿡 ����Ʈ�����ʿ� ��ü �߰� 
						totalL.addListSelectionListener(new ListListener());
						movieL.addListSelectionListener(new ListListener());
						bookL.addListSelectionListener(new ListListener());
						searL.addListSelectionListener(new ListListener());
						JButton add2 = new JButton("�߰�");
						button.add(add2);
						
						east.add(button, BorderLayout.SOUTH);					
						movievec = new ItemCollections(movie);
						bookvec = new ItemCollections(book);				
						 // ��� ���̾�α� �߰�
						MyModalDialog dialog = new MyModalDialog(this, "�Է�");
						// �߰� ��ư�� �׼Ǹ����� �߰�
					        add2.addActionListener(new ActionListener() { 				        	
					              @Override
					        	public void actionPerformed(ActionEvent e) { 
					        	dialog.setVisible(true); // ��� ���̾�α� ���̵��� ����
					           // ���õ� ���̿� ��ư�� ���� ��޴��̾�α��� ���� ������ Item ��ü ���� �� vector�� �߰�
					        	if(dialog.getInputbRadioB() == "Movie") {			  
						        	String movi[];
						        	movi = dialog.getInputMovie();					        	
						        	// Movie ��ü �����
						            Movie k = new Movie();
						            //���� ������ Item ��ü�� ���� �ֱ�
						            k.set(movi[0], movi[1], movi[3], movi[4], movi[5], dialog.getInputdate(), dialog.getInputscor(), 1, dialog.getyearmIndex(), dialog.getInputscorm2());
						            k.setMovie(movi[2], dialog.getInputSort(), dialog.getInputage(), dialog.getsortIndex(), dialog.getagecIndex());
						            // total, movie ���Ϳ� �߰� �� �� ����Ʈ ����	      
						        	movievec.Iadd(k);				        			   
						        	total.add(k);				        	
						        	totalL.setListData(total);					        	
						        	movieL.setListData(movie);			            
					        	   }
					        	
						        if(dialog.getInputbRadioB() == "Book") {
						        	String boo[];
					        	boo = dialog.getInputBook();				        	
					            Book k2 = new Book();
					            //���� ������ Item ��ü�� ���� �ֱ�
					            k2.set(boo[0], boo[1], boo[3], boo[4], boo[5], dialog.getInputbdate(), dialog.getInputscor2(), 2, dialog.getyearbIndex(), dialog.getInputscorb2());
					            k2.setMovie(boo[2]);
					            Boolean bt = k2.equals("");				            
					            // total, movie ���Ϳ� �߰� �� �� ����Ʈ ����	    
					            bookvec.Iadd(k2);		   
					        	total.add(k2);
					        	totalL.setListData(total);
					        	bookL.setListData(book);
						         }				        
						        
					              }	
					        	});				        
					        
						// �� �� �ҳ� �߰�				
						JPanel top = new JPanel();
						top.setLayout(new FlowLayout(FlowLayout.LEFT,330,0));					
						JLabel note = new JLabel("My Notes");
						// note �� font ����
						note.setFont(new Font("���", Font.ITALIC + Font.BOLD, 35));					
						note.setForeground(Color.BLUE);
						top.add(note);
						top.add(new MyTimeLabel());
						c.add(top, BorderLayout.NORTH);					
						
						// �� ���� �ҳ� �ۼ�
						p3 = new JPanel(); 
						
						Border resultBorder = BorderFactory.createTitledBorder("�� ����");
						Border resultBorder2 = BorderFactory.createTitledBorder("�ٰŸ�");
						Border resultBorder3= BorderFactory.createTitledBorder("������");
						// ������ ���� 
						JPanel whole = new JPanel();
						whole.setLayout(new BorderLayout(0,1));
						p3.setLayout(null);
						JPanel mid = new JPanel();
						JPanel review = new JPanel();
					    
					    grid = new GridLayout(7, 2);
					    grid.setVgap(5);				    
					    grid2 = new GridLayout(5, 2);
					  
					    imageLabel.setIcon(changeIconb); // �̹��� �߰�
			           	           
						mi = new JTextArea("", 9, 65);
						rev = new JTextArea("", 9, 65);
						// �� textArea�� �׵θ�, ��ũ�� �߰�
						mid.setBorder(resultBorder2);
						mid.add(new JScrollPane(mi));					
						review.setBorder(resultBorder3);
						review.add(new JScrollPane(rev));
						
						imageLabel.setLocation(5,30); imageLabel.setSize(230, 275);
						
						mid.setLocation(5, 310); mid.setSize(780, 210);
						review.setLocation(5, 525);	review.setSize(780, 210);
						
					    p3.add(mid); p3.add(review);  p3.add(imageLabel);
				        // �� ��� ��ġ ���� �� ������ ���� �� �߰�
						p3.setBorder(resultBorder);
						//������ ��ο� �߰�
						JPanel button2 = new JPanel();		
						JButton edit = new JButton("����");
						JButton del = new JButton("����");
			            button2.add(edit);
			            button2.add(del);
			            
			            MyModalDialog dialog2 = new MyModalDialog(this, "�Է�");
					     // ��� ���̾�α� �߰�			      
					       // ���� ��ư�� �׼� ������ �߰�  		            
			           edit.addActionListener(new ActionListener() { 			        	
				              @Override
				        	public void actionPerformed(ActionEvent e) { 		            	 
				            	
				            	  if(find.getDit() == 1) 
				            		  dialog2.setMovie(find);
				            	  if(find.getDit() == 2) 
				            		  dialog2.setbook(find);
				            		  			            	  
				            	  dialog2.setVisible(true); 
				            	  // ���õ� ��ü�� ��ȭ�� ���
				            	  if(find.getDit() == 1) {
				            		  
				            		  String movi[];
							        	movi = dialog2.getInputMovie();
							        
							        	// �̹��� ������ ���� 
							        	int totalindex = total.indexOf(find);
							        	int moviindex = movie.indexOf(find);
							      
							        	// Movie ��ü �����
							            Movie k = new Movie();
							            k.set(movi[0], movi[1], movi[3], movi[4], movi[5], dialog2.getInputdate(), dialog2.getInputscor(), 1, dialog2.getyearmIndex(), dialog2.getInputscorm2());
							            k.setMovie(movi[2], dialog2.getInputSort(), dialog2.getInputage(), dialog2.getsortIndex(), dialog2.getagecIndex());
							        
							            // ���� ��� vector�� �߰�			      
							        	movievec.Iedit(moviindex, k);;				        			   
							        	total.set(totalindex, k);				        	
							        	totalL.setListData(total);					        	
							        	movieL.setListData(movie);	
							            
										p3.remove(pre); // p3  �ҳο��� ���� �� ���� �ҳ� ����
										JPanel now = new JPanel();
										dist2 = dePanel(k, now, dist2);	// �ش� �׸��� ����� ������ �ҳ� p3�� �߰� �ϴ� �Լ�
				            	  }
				            	// ���õ� ��ü�� å�� ���
				            	  if(find.getDit() == 2) {
				            		  String boo[];
							        	boo = dialog2.getInputBook();						   
							            Book k2 = new Book();
							            k2.set(boo[0], boo[1], boo[3], boo[4], boo[5], dialog2.getInputbdate(), dialog2.getInputscor2(), 2, dialog2.getyearbIndex(), dialog2.getInputscorb2());
							            k2.setMovie(boo[2]);
							            Boolean bt = k2.equals("");
							            int totalindex = total.indexOf(find);
							        	int booindex = book.indexOf(find);
							        	// ���� ��� vector�� �߰�
							            bookvec.Iedit(booindex, k2);;				        			   
							        	total.set(totalindex, k2);				        	
							        	totalL.setListData(total);					        	
							        	bookL.setListData(book);	
							            
	                                    p3.remove(pre);   // p3  �ҳο��� ���� �� ���� �ҳ� ����
										JPanel now = new JPanel();   // �ش� �׸��� ����� ������ �ҳ� p3��
										dist2 = dePanel(k2, now, dist2);
				            	  }
				            	  
				              }
				        	});
			        // ���� ��ư�� �׼� ������ �߰�  
			           del.addActionListener(new ActionListener() { 
				        	
				              @Override
				        	public void actionPerformed(ActionEvent e) { 
				            	  
				            	  int result = JOptionPane.showConfirmDialog(null,"���� �����Ͻðڽ��ϱ�?", "���� Ȯ��", JOptionPane.YES_NO_OPTION);
									if(result == JOptionPane.CLOSED_OPTION) // ����ڰ� ���þ��� ���̾�α� â�� ���� ���
									{
									    	
									}
									else if(result == JOptionPane.YES_OPTION) // Yes�� ������ ����
									{
										 if(dist2 == 1 && n != -1) { // ��ü�� ��ȭ�� ���
							        		   total.remove(find);
							        		   movievec.Idelete(find);
							        		   totalL.setListData(total);
							        		   movieL.setListData(movie);
							        		   //�� ���Ϳ��� ��ü ���� �� ���� 
							        		   mi.setText("");						        		   
							        		   rev.setText(""); 
							        		   imageLabel.setIcon(changeIconb);
							        		   p3.remove(pre);
							        		   // TextArea, �̹��� ������, �󼼺��� �ҳ� p3 ����
							        		   repaint();
							        		   
							        	   }
							        	   if(dist2 == 2 && n != -1) {// ��ü�� ��ȭ�� ���
							        		   total.remove(find);
							        		   bookvec.Idelete(find);
							        		   totalL.setListData(total);
							        		   bookL.setListData(book);
							        		 //�� ���Ϳ��� ��ü ���� �� ���� 
							        		   mi.setText("");
							        		   rev.setText("");
							        		   imageLabel.setIcon(changeIconb);
							        		   p3.remove(pre);
							        		// TextArea, �̹��� ������, �󼼺��� �ҳ� p3 ����
							        		   repaint();
							        	   } 	
									}
									else { // No�� ������ ���
										
									}
				              }
				        	});
						
						whole.add(button2,BorderLayout.SOUTH);
						whole.add(p3, BorderLayout.CENTER);					
						
						c.add(east, BorderLayout.WEST); // ���� �߰�
						c.add(whole, BorderLayout.CENTER);
						c.add(top, BorderLayout.NORTH);					
						
						setSize(1100, 900); // �������� ũ�Ⱑ 250 x 300
						setVisible(true);    // ������ ���
						
						
					}
					JPanel pre; // ���� ���õ� �󼼺��� �г�		
					int dist2 = 0; // �� �г� ��ä�� ��Ÿ���� ����
					int count = 0;
					Item find; 
					//����Ʈ �׸� ���ý� �󼼺��� �г� �߰�
					public class ListListener implements ListSelectionListener{
						 
		                 JList<Item> k;
						@Override
						public void valueChanged(ListSelectionEvent e) {
							k =(JList<Item>) e.getSource();
							Item ite = (Item) k.getSelectedValue();
						      int index = k.getSelectedIndex();					      
						      n = index;					     
						      if(k.getSelectedIndex() != -1) { // getSelectedIndex�� != -1 �� ��쿡�� 
							
						    	  find = ite;	
							if(dist2 != 0) {
							p3.remove(pre);		
							}  // dist�� 0 �� �ƴϸ� ����
							
							JPanel now = new JPanel();
							dist2 = dePanel(ite, now, dist2); // p3 �ҳο� ������ ��ü�� �������� ���� now �ҳ��� p3�� �߰�		
													
						      }
						}
					}
				   // �󼼺��⸦ ����� �Լ�
					// p3 �ҳο� ������ ��ü�� �������� ���� now �ҳ��� p3�� �߰�
					public int dePanel(Item ite, JPanel now, int dist2) {
						Movie mt;
						Book bt;
					   if(ite.getDit() == 1) { // ��ü�� ��ȭ�� ���
						   mt = (Movie)ite; 
						   String ml[] = mt.getAuthor();
						   // ��ü�� ���� ������ ������ �����ϱ�
						   String ml2[] = mt.getele();
						   
						   dist2 = 1;
						   mi.setText(ml[5]);
						   rev.setText(ml[6]);
						   
						   ico2 = new ImageIcon(ml[3]); // �̹��� ������ �߰�
						   Image img = ico2.getImage();
						    Image changeImage = img.getScaledInstance(230, 275, Image.SCALE_SMOOTH);
						    // �̹��� ������ ���� 
						    ImageIcon changeIcon = new ImageIcon(changeImage);
						    // �̹��� �����ܿ� ������ ��ü 
						   imageLabel.setIcon(changeIcon);	 
						   // TextArea�� �ٰŸ��� ���� �߰�
						    now.setLayout(grid); 				            
						   
						   for(int i = 0; i < 7; i++) {							   
							   infor[i] = new JLabel(infors[i]);
							   if(i == 3) {
								   infor2[i] = new JLabel(ml2[0]);
							   }
							   else if(i == 4) {
								   infor2[i] = new JLabel(ml2[1]);
							   }
							   else if(i == 0 || i == 1) {
								   infor2[i] = new JLabel(ml[i]);
							   }
							   else if(i == 2)
								   infor2[i] = new JLabel(ml2[2]);
							   else if(i == 5)
								   infor2[i] = new JLabel(ml[2] + "�⵵");
							   else
								   infor2[i] = new JLabel(ml[4] + "��");
							   now.add(infor[i]);
				               now.add(infor2[i]);			               
					           
						   } // �ҳο� �󼼺��� Label ���� �� �߰�
						   now.setLocation(245,30); now.setSize(200, 250);
						   p3.add(now);
						   pre = now;
						   // ��ġ ���� �� p3 �ҳο� now �ҳ� �߰�
						   if(dist2 != 0)
							   repaint();
					   }   // �ٽ� �׸���
					   // å ��ü�� ���� �Ǿ��� ��� 
					   if(ite.getDit() == 2) {  
						   bt = (Book)ite;
						   // ��ü�� ���� ������ ������ �����ϱ�
						   String bl[] = bt.getAuthor();
						   String bl2 = bt.getPubli();
						   mi.setText(bl[5]);
						   rev.setText(bl[6]);
						   ico2 = new ImageIcon(bl[3]);
						   Image img = ico2.getImage();
						    Image changeImage = img.getScaledInstance(230, 275, Image.SCALE_SMOOTH);
						   // �̹��� ������ ����
						    ImageIcon changeIcon = new ImageIcon(changeImage);
						    // �̹��� �����ܿ� ������ ��ü 
						   imageLabel.setIcon(changeIcon);
				            
						   now.setLayout(grid2);						  						   
						   dist2 = 2;
						   // now �ҳο� �󼼺��� �� �߰�
						   for(int i = 0; i < 5; i++) {
							  inforb[i] = new JLabel(inforsb[i]);
							   if(i == 2) {
								   inforb2[i] = new JLabel(bl2);
							   }
							   else if(i == 4) {
								   inforb2[i] = new JLabel(bl[4] + "��");
							   }
							   else if(i == 0 || i == 1) 
								   inforb2[i] = new JLabel(bl[i]);
							   
							   else
								   inforb2[i] = new JLabel(bl[2] + "�⵵");
							   now.add(inforb[i]);
				               now.add(inforb2[i]);			               
					           
						   }  // �ҳο� �󼼺��� Label ���� �� �߰� 
						   now.setLocation(245,30); now.setSize(210, 250);
						   p3.add(now);
						   pre = now;												  						  
						
						   if(dist2 != 0)
							   repaint();
						// ��ġ ���� �� p3 �ҳο� now �ҳ� �߰�
					   }				
					   return dist2;
						
					}
					
				// �ð��� ��Ÿ���� MyTimeLabel �Լ�
				class MyTimeLabel extends JLabel implements Runnable {
					private Thread timerThread = null;
					public MyTimeLabel() {
						setText(makeClockText());
						setFont(new Font("TimesRoman", Font.ITALIC, 22));
						setHorizontalAlignment(JLabel.CENTER);
						timerThread = new Thread(MyTimeLabel.this);
						timerThread.start();
					}
					
					public String makeClockText() {
						Calendar c = Calendar.getInstance();
						int year = c.get(Calendar.YEAR);
						int month = c.get(Calendar.MONTH) + 1;
						int day = c.get(Calendar.DAY_OF_MONTH);
						int hour = c.get(Calendar.HOUR_OF_DAY);
						int min = c.get(Calendar.MINUTE);
						int second = c.get(Calendar.SECOND);
						// �� ��¥ �� �ð� ���� ��������						
						String date = year + "�� " + month + "�� " + day + "�� " + hour + ":" + min + ":" + second; 
						// date�� �ð� ���� �ۼ�
						return date;
					}
					
					@Override
					public void run() {
						while(true) {
							try {
								Thread.sleep(1000);
							}
							catch(InterruptedException e){return;}
							setText(makeClockText());
						}					
				}
				}
					// �޴� ���� �Լ�
					void creatMenu() { 
						JMenuItem [] mI = new JMenuItem[4];
						String[] itemT = {"�ҷ�����", "�����ϱ�", "����", "�ý��� ����"};
						JMenuBar mb = new JMenuBar(); // JMenuBar ������Ʈ ����
						
						
						JMenu fileM = new JMenu("����");
						JMenu helpM = new JMenu("����"); // JMenu ������Ʈ ����
						// �޴� ������ ���� �� JMenu�� ���̱�
						for(int i = 0; i < 4; i++) {
							mI[i] = new JMenuItem(itemT[i]);
							if(i == 1) {
								fileM.add(mI[i]);
								fileM.addSeparator();  // �и��� ����
							}
							else if(i < 3) fileM.add(mI[i]);
							else helpM.add(mI[i]);
						}
						 						
						// ���� �޴��� Ȯ�� ���̾�α� �߰�
						mI[2].addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent e) {
							int result = JOptionPane.showConfirmDialog(null,"�����Ͻðڽ��ϱ�?", "���� Ȯ��", JOptionPane.YES_NO_OPTION);
							if(result == JOptionPane.CLOSED_OPTION) // ����ڰ� ���þ��� ���̾�α� â�� ���� ���
							{
							    	
							}
							else if(result == JOptionPane.YES_OPTION) // Yes�� ������ ����
							{
								System.exit (0);  	
							}
							else { // �ƴϿ��� ������ ���
							
							}
							}
						});				
				        
				     // ���� �޴��� �޽��� ���̾�α� �߰�				     	
				        mI[3].addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent e) {
								JOptionPane.showMessageDialog(null, "MyNotes �ý��� v 1.0 �Դϴ�.", "Message", JOptionPane.INFORMATION_MESSAGE);
							}
						});
				        
				     // ���� ���̾�α�				        
				      // ���� �޴��� ���� ���� ���̾�α� �߰�
				        mI[0].addActionListener(new OpenActionListener()); 
				     // ���� �޴��� ���� ���� ���̾�α� �߰�
				        mI[1].addActionListener(new OpenActionListener()); 				
						mb.add(fileM);
						mb.add(helpM);      // �޴� �ٿ� JMenu�� ���̱�  						
						setJMenuBar(mb);
					}
					
					// ����
					JTabbedPane ResultTabbedPane() {
						JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP);
						totalL.setVisibleRowCount(30); 
						totalL.setFixedCellWidth(280);
						searL.setVisibleRowCount(0); 
						searL.setFixedCellWidth(100); 
						search.setLayout(new BorderLayout());
						search.add(searL, BorderLayout.CENTER);
						creatToolBar(); // ���ٸ� ����� �Լ�
						   // �� ����� ���ҿ� ���̱�
							pane.addTab(tp[0], totalL); 
							pane.addTab(tp[1], movieL);
							pane.addTab(tp[2], bookL);
							pane.addTab(tp[3], search);						
						return pane;
					}
					
					void creatToolBar() { // �˻� ����Ʈ�� ���� �����
						JToolBar toolBar = new JToolBar("search");
						toolBar.setBackground(Color.LIGHT_GRAY);
						Vector<Item> searchv = new Vector<Item>();
						JComboBox<String> searSelect = new JComboBox<String>(); // �˻� �ǿ� ���� �޺��ڽ� ����
						searSelect.addItem("����");
						searSelect.addItem("����");    // �޺��ڽ��� ��� �߰�
						JTextField searT = new JTextField(12);
						JPanel sbutton = new JPanel();
						JButton searB = new JButton("�˻�");	// ���� ��� ��ü �����
						 // �˻� ��ư�� �׼� ������ �߰�
						searB.addActionListener(new ActionListener() { 
				        	
				              @Override
				        	public void actionPerformed(ActionEvent e) { 				        
				            	  String stext = searT.getText(); 
				            	  String sselect = searSelect.getSelectedItem().toString();
				            	  Vector<Item> searchv = new Vector<Item>(); 			            	  
				            	  Item stotal;
				            	  int coun = 0;				            	  
				            	  // ������ ��� �˻�
				            	  if(sselect.equals("����")) {		          		  
				            		  			            			  
				            			  for(int i = 0; i < total.size(); i++) {
				            				  stotal = total.get(i);
				            				  // ������ �˻� ���ڿ��� ���� �� ���
				            				  if(stotal.toString().contains(stext)) {
				            					  searchv.add(stotal); // ���Ϳ� �߰�
				            					  coun++;
				            				  }			            					  
				            		  }
				            			  searL.setListData(searchv); 
				            			  if(coun == 0) { // �˻� ����� ���� ��� 
				            				  JOptionPane.showMessageDialog(null, "[" + stext + "]" +" �˻� ����� �����ϴ�.", "Message", JOptionPane.INFORMATION_MESSAGE);
				            			  }
				            	  }
				            	  // �������� �˻�
				            	  if(sselect.equals("����")) {
				            	  int k = Integer.parseInt(stext);
				            	  for(int i = 0; i < total.size(); i++) {
		            				  stotal = total.get(i);
		            				// �˻��� �̻��� �׸��� ���
		            				  if(stotal.getscor() >= k) {
		            					  searchv.add(stotal); // ���Ϳ� �߰�
		            					  coun++;
		            				  }			            					  
		            		  }
				            	  searL.setListData(searchv); 
				            	  if(coun == 0) { // �˻� ����� ���� ��� 
		            				  JOptionPane.showMessageDialog(null, " �˻� ����� �����ϴ�.", "Message", JOptionPane.INFORMATION_MESSAGE);
		            			  }
				            	  }				            	  
				              }						          	
				        	});
						
						toolBar.add(searSelect);
						toolBar.add(searT);
						sbutton.add(searB);
						toolBar.add(sbutton);  // toolBar�� ��� �߰�
						search.add(toolBar, BorderLayout.NORTH);
						// toolBar ���ʿ� �߰� 
					}
					
					class OpenActionListener implements ActionListener { 
						
						private JFileChooser chooser = new JFileChooser(); 
						
						public void actionPerformed(ActionEvent e) { 
							
							String cmd = e.getActionCommand();	
							if(cmd.equals("�ҷ�����")) {
								int f = chooser.showOpenDialog(null); 
								// �ҷ����� ���̾�α� ���
								if(f != JFileChooser.APPROVE_OPTION) { 
									JOptionPane.showMessageDialog(null, "������  ��������  �ʾҽ��ϴ�", "���", JOptionPane.WARNING_MESSAGE); 
									return; 
									} 
								if(f == JFileChooser.APPROVE_OPTION) { 
									String patName = chooser.getSelectedFile().getPath();
									try {
										file(patName);
										
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								} // ����ڰ� ������ ���� �̸� �˾Ƴ� �� ���� ����
							}
							
							if(cmd.equals("�����ϱ�")) {
								int f = chooser.showOpenDialog(null); 
								// �����ϱ� ���̾�α� ���
								if(f != JFileChooser.APPROVE_OPTION) { 
									JOptionPane.showMessageDialog(null, "������  ��������  �ʾҽ��ϴ�", "���", 
									JOptionPane.WARNING_MESSAGE); 
									
									return; 
									} 
								if(f == JFileChooser.APPROVE_OPTION) { 
									String patName = chooser.getSelectedFile().getPath();
									
									try {
										savefile(patName);
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								} // ����ڰ� ������ ���� �̸� �˾Ƴ� �� ���� ����
							}
								
							}
							}
				
				public void savefile(String patName) throws Exception{
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("����.dat")); // ���� ����
					// ���� ���� ����
					oos.writeObject(total);  // ���Ͽ� �÷��� ����
					oos.close();				
				}
				
				public void file(String patName) throws Exception{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("����.dat")); 
	        	// ���� ���� ����
	        	total = (Vector<Item>)ois.readObject();
	        	// total�� ��ü ����
			    ois.close(); // ���� �ݱ�
			    totalL.setListData(total);  // totalL ����Ʈ ����			    
			    
			    for(int i = 0; i < total.size(); i++) {
			    	
			    	if((total.get(i).getDit()) == 1)
			    		movievec.Iadd(total.get(i)); // ��ȭ�� ��� movie ���Ϳ� Item �߰�
			    	else
			    		bookvec.Iadd(total.get(i));// å�� ��� book ���Ϳ� Item �߰�
			    	
			    	 movieL.setListData(movie);	
			    	 bookL.setListData(book);	// ����Ʈ ����	
			    }	        
				}
				
					public static void main(String[] args) throws Exception{
					
						new FinalProject();
				
					}
				}
				
				
