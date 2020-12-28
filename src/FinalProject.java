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
			    // 영화, 책 구별 수
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
				//필요한 변수들 getter함수   
		        			
				@Override
				public String toString() {
					return title;
				}  // toString() 오버라이드
						   
			}
			
			// Item 클래스를 상속하는 subclass Movie 클래스 작성
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
			// Item 클래스를 상속하는 subclass Book 클래스 작성
			class Book extends Item implements Serializable{
				String publi;
			   public void setMovie(String publi) {
				this.publi = publi;
				}			
				public String getPubli() {
					return publi;
				}
				
			}
			
			// ItemCollections 클래스 정의
			class ItemCollections{
				Vector<Item> v = new Vector<Item>();
				
				public ItemCollections(Vector<Item> v) {
					this.v = v;
				}
				public void Iadd(Item m) {
					v.add(m);
				} // Item v에 추가하는 함수
				
				public void Idelete(Item search){
					v.remove(search);
				}     // Item v에 삭제하는 함수
				public void Iedit(int n, Item edit) {
					v.set(n, edit);			
				}    // Item v에 수정하는 함수				
				
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
				String textms [] = {"제목", "감독", "배우", "장르", "등급", "개봉년도", "포스터", "별점", "줄거리", "감상평"}; 
				String textbs [] = {"제목", "저자", "출판사", "출판년도", "책이미지", "별점", "책소개", "감상평"}; 
				String sort []= {"드라마", "코미디", "미스터리, 스릴러", "범죄", "애니메이션", "액션"};
				String age [] = {"전체", "12세 이상", "15세 이상", "청소년 관람 불가"};
				String postIfn, postIbfn;
				JTextField postIt, postbIt;
				
				// 모달 다이얼로그 작성
				public MyModalDialog(JFrame frame, String title) { 
					super(frame,title, true); // 모달 다이얼로그로 설정 
					setLayout(new BorderLayout()); 
					
							
					JPanel bookt = new JPanel();
					JPanel RadioB = new JPanel();
					ButtonGroup g = new ButtonGroup();
					Border resultBorder = BorderFactory.createTitledBorder("영화 정보");
					Border resultBorder2 = BorderFactory.createTitledBorder("책 정보");
					
					GridLayout grid2 = new GridLayout(0, 2);
				    grid2.setVgap(3);
				    
				    // 라디오 버튼이 Movie일 때의 UI 작성
				    JPanel moviet = new JPanel();
				    JPanel movieCen = new JPanel();
				    JPanel moviebott = new JPanel();
					moviet.setLayout(null);
					movieCen.setLayout(null);
					moviebott.setLayout(null);
					// 배치 관리자 삭제
					
					JPanel lef = new JPanel();
					JPanel rig = new JPanel();
				    GridLayout grid = new GridLayout(0, 1);
				    grid.setVgap(3);
				    lef.setLayout(grid);		   
				    //영화 라벨 객체 생성 및 팬널에 추가
				    for(int i = 0; i < 8; i++){
				    	textm[i] = new JLabel(textms[i]);
				    	lef.add(textm[i]);
				    } 
				    
				    moviet.setBorder(resultBorder);
				    rig.setLayout(grid);
				    
				    // 각각의 요소들의 객체 생성 설정
				    for(int i = 0; i < 3; i++) {
						Text[i] = new JTextField(25);
						rig.add(Text[i]);
					}
				    // 각각 장르, 나이, 년도에 해당하는 콤보박스 요소 추가
					for(int i = 0; i < sort.length; i++) 
						sortc.addItem(sort[i]);
			       
			        for(int i =0; i < age.length; i++) 
						agec.addItem(age[i]);
			     
			        for(int i = 20; i >= 0; i--) 
						yearm.addItem(2000 + i);
			       
			        // 포스터 이미지
			        JPanel postI = new JPanel();
			        postIt = new JTextField(15);
			        // 불러오기 버튼을 누르면 포스터 이미지의 파일 주소 리턴
			        JButton pimagf = new JButton("불러오기");
			        // 액션 리스너 작성
			        pimagf.addActionListener(new ActionListener() {
			        	private JFileChooser chooser = new JFileChooser(); 
			        	 public void actionPerformed(ActionEvent e) { 	     		
			     		
			     				int f = chooser.showOpenDialog(null); 
			     				// 열기 다이얼로그 출력
			     				if(f != JFileChooser.APPROVE_OPTION) { 
			     					JOptionPane.showMessageDialog(null, 
			     					"파일을  선택하지  않았습니다", "경고", 
			     					JOptionPane.WARNING_MESSAGE); 
			     					return; 
			     					} 
			     				if(f == JFileChooser.APPROVE_OPTION) { 
			     					String patName = chooser.getSelectedFile().getPath();
			     					postIt.setText(patName);
			     					
			     				} // 사용자가 선택한 파일 이름 알아내 텍스트 필드 내용 변경
			     			}	     	      
			        });
			        // movie에 해당하는 JSlider 생성 후 설정
			        scom = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
			        scom.setPaintLabels(true);
			        scom.setPaintTicks(true);
			        scom.setPaintTrack(true);
			        scom.setMajorTickSpacing(1);
			        scom.setMinorTickSpacing(1);
			        
			        postI.add(postIt); postI.add(pimagf);
			        rig.add(sortc);rig.add(agec); rig.add(yearm);
			        rig.add(postI); rig.add(scom);
			        //각 요소 rig에 추가
			       
			        lef.setLocation(40,20);	lef.setSize(50, 400);		        
					rig.setLocation(120,20);  rig.setSize(300, 405);
					// lef, rig 팬널 크기, 위치 설정
				    movieCen.add(lef);  movieCen.add(rig);
				    
				    movieCen.setLocation(10,20);
					movieCen.setSize(500, 430);			    		
				    moviet.add(movieCen);	    
				    // 줄거리와 감상평에 해당하는 TextArea 추가			    
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
			    	
			    	
			    	// 콤보박스가 Book일 때의 UI 작성
				    JPanel bookCen = new JPanel();
				    JPanel bookbott = new JPanel();
					
					bookCen.setLayout(null);
					bookbott.setLayout(null);
					// 배치 관리자 삭제
					JPanel lefb = new JPanel();
					JPanel rigb = new JPanel();
				    lefb.setLayout(grid);
			
				    for(int i = 0; i < 6; i++){
				    	textb[i] = new JLabel(textbs[i]);
				    	lefb.add(textb[i]);
				    }
				    // 라벨 객체 생성 후 lefb 팬낼에 추가    
				    rigb.setLayout(grid);
				    
				    for(int i = 3; i < 6; i++) {
						Text[i] = new JTextField(25);
						rigb.add(Text[i]);
					} // 제목, 저자, 출판사에 해당하는 텍스트 필드 객체 생성후 rigb 팬널에 추가
				
			        for(int i = 20; i >= 0; i--) 
						yearb.addItem(2000 + i);
			        // 년도에 해당하는 콤보박스 요소 추가 
			        scom2 = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
			        scom2.setPaintLabels(true);
			        scom2.setPaintTicks(true);
			        scom2.setPaintTrack(true);
			        scom2.setMajorTickSpacing(1);
			        scom2.setMinorTickSpacing(1);
			        // 별점에 해당하는 JSlider 객체 생성 및 설정 
			        
			        // 포스터 이미지 
			        JPanel postbI = new JPanel();
			        postbIt = new JTextField(15);
			        // 선택한 파일 이름 가져오기 
			        JButton pimagbf = new JButton("불러오기");
			        pimagbf.addActionListener(new ActionListener() {
			        	private JFileChooser chooser = new JFileChooser(); 
			        	 public void actionPerformed(ActionEvent e) { 	     		
			     		
			     				int f = chooser.showOpenDialog(null); 
			     				// 불러오기 다이얼로그 출력
			     				if(f != JFileChooser.APPROVE_OPTION) { 
			     					JOptionPane.showMessageDialog(null, 
			     					"파일을  선택하지  않았습니다", "경고", 
			     					JOptionPane.WARNING_MESSAGE); 
			     					return; 
			     					} 
			     				if(f == JFileChooser.APPROVE_OPTION) { 
			     					String patName = chooser.getSelectedFile().getPath();
			     					postbIt.setText(patName);
			     					
			     				} // 사용자가 선택한 파일 이름 알아내 텍스트 필드 내용 변경
			     			}
			     	      
			        });
			        					
			        // 팬널에 지금까지의 요소 추가 및 위치, 사이즈 설정
			        postbI.add(postbIt);  postbI.add(pimagbf);
			        rigb.add(yearb); rigb.add(postbI);  rigb.add(scom2);		       
			        lefb.setLocation(40,20); lefb.setSize(50, 320);		        
					rigb.setLocation(120,20); rigb.setSize(300, 330);
					
				    bookCen.add(lefb);  bookCen.add(rigb);			    
				    bookCen.setLocation(10,20);  bookCen.setSize(500, 340);			   			
					bookt.setLayout(null); bookt.setBorder(resultBorder2); bookt.add(bookCen);
				    // 줄거리와 감상평에 해당하는 TextArea 추가
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
			    		       
			        // 라디오 버튼 생성 후 추가, 리스너 추가
				    for(int i = 0; i < 2; i++) {
						radi[i] = new JRadioButton(radiot[i]);
						g.add(radi[i]);
						RadioB.add(radi[i]);
						int countm = 0;
						int countb = 0;
						//라디오 버튼을 누를 때 해당 라디오 버튼에 해당하는 Jpanel 보이도록 하는 액션 리스너 
						radi[i].addItemListener(new ItemListener() {
							@Override
							
							public void itemStateChanged(ItemEvent e) {
								 // 라디오 버튼이 선택 해제 될 경우
								if(e.getStateChange() == ItemEvent.DESELECTED) {								
									JRadioButton k = (JRadioButton)e.getSource();
									String s = k.getText();
									if(s.equals("Movie")) // 해제 버튼이 영화 일 경우 해당 팬널 숨김
										moviet.setVisible(false);							    
									if(s.equals("Book")) // 해제 버튼이  책 일 경우 해당 팬널 숨김
									    bookt.setVisible(false);									
									return;
								}
								if(radi[0].isSelected()) { // Movie 라이오 버튼이 눌리면 해당 판넬 보이도록
									if(countm == 0)
									add(moviet,BorderLayout.CENTER);
									moviet.setVisible(true);
									
								}
								else {
									if(countb == 0)  // Book 라이오 버튼이 눌리면 해당 판넬 보이도록
									add(bookt,BorderLayout.CENTER);
									bookt.setVisible(true);
									
								}
							}
							
						});
					}
				    radi[0].setSelected(true); // 첫번째 라디오 버튼 누른 상태로 설정
					// 라디오 버튼과 설정 버트 추가
				    add(RadioB,BorderLayout.NORTH);
					JPanel p3 = new JPanel();
					JButton okb = new JButton("OK");  // 설정 버튼 추가
					p3.add(okb);
					add(p3, BorderLayout.SOUTH);
				
				setSize(500, 800); 
				// OK 버튼이 눌릴 경우 액션 리스너
				okb.addActionListener(new ActionListener() { 
					public void actionPerformed(ActionEvent e) { 
			             setVisible(false);
			            // 모달 다이얼로그 안보이도록 설정
				} 				
				}); // JDialog를 상속받아 다이얼로그 만들기			
				} 
				// Movie의 텍스트 필드와 텍스트 필드의 내용이 들어간 String 배열 리턴
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
				// 장르 정보 리턴
				public String getInputSort() {
					String sort = sortc.getSelectedItem().toString();				
					 return sort;				 
				} 
				// 연령 등급 리턴
				public String getInputage() {
					String age = agec.getSelectedItem().toString();				
					 return age;
				} 
				//영화 개봉년도 리턴
				public String getInputdate() {
					String year = yearm.getSelectedItem().toString();				
					 return year;
					 	} 
				//영화 별점 문자열로 바꿔 리턴
				public String getInputscor() {
					 return Integer.toString(scom.getValue());
					 	} 
				//영화 별점 리턴
				public int getInputscorm2() {
					 return scom.getValue();
					 	} 
				// 책 별점 문자열로 바꿔 리턴
				public String getInputscor2() {
				 	 return Integer.toString(scom2.getValue());
					 	} 
				// 책 별점 리턴
				public int getInputscorb2() {
					 return scom2.getValue();
					 	} 
				// 장르의 인덱스 리턴
				public int getsortIndex() {		
					return sortc.getSelectedIndex();
				}
				//영화 개봉년도의 해당 인덱스 리턴
				public int getyearmIndex() {		
					return yearm.getSelectedIndex();
				}
				// 영화 등급 인덱스 리턴
				public int getagecIndex() {		
					return agec.getSelectedIndex();
				}
				// 책 개봉년도 해당 인덱스 리턴
				public int getyearbIndex() {		
					return yearb.getSelectedIndex();
				}
				// Book의 텍스트 필드와 텍스트 필드의 내용이 들어간 String 배열 리턴
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
				// 영화 개봉년도 리턴
				public String getInputbdate() {
					 return yearb.getSelectedItem().toString();
				} 		
				//선택된 라디오 버튼에 해당하는 문자열 리턴 
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
				
				public void setMovie(Item item) { // 영화 정보 가져오기
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
				
				public void setbook(Item item) { // 책 정보 가져오기
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
				    String tp[] = {"전체", "영화", "도서", "검색"};
				    JTextArea mi, rev;
				    JPanel text, text2, p3;
				    int dist = 0;
				    int n;
					ImageIcon ico = new ImageIcon("C:\\Users\\user\\Desktop\\자바 수업\\LineFinalProject\\images\\이미지없.jpg");
					ImageIcon ico2;
					Image imgb = ico.getImage();
				    Image changeImageb = imgb.getScaledInstance(230, 275, Image.SCALE_SMOOTH);
				    // 이미지 사이즈 변경 
				    ImageIcon changeIconb = new ImageIcon(changeImageb);
				    // 이미지 아이콘에 아이콘 객체 				   				
				    
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
				    String infors[] = {"제목", "감독", "배우", "장르", "등급", "개봉년도", "별점"};
				    String inforsb[] = {"제목", "저자", "출판사", "출판년도", "별점"};
				    
					public FinalProject() throws IOException {
						setTitle("JAVA 05 1812545 홍다혜");
						setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						Container c = getContentPane(); 
						c.setLayout(new BorderLayout());	
						 					 
						creatMenu();  // 메뉴를 만드는 함수 
						
						JTabbedPane pane = ResultTabbedPane();
						
						JPanel east = new JPanel();
						JPanel button = new JPanel();
						east.setLayout(new BorderLayout(0,1));
						east.add(pane, BorderLayout.CENTER);					
					    // JList들에 리스트리스너에 객체 추가 
						totalL.addListSelectionListener(new ListListener());
						movieL.addListSelectionListener(new ListListener());
						bookL.addListSelectionListener(new ListListener());
						searL.addListSelectionListener(new ListListener());
						JButton add2 = new JButton("추가");
						button.add(add2);
						
						east.add(button, BorderLayout.SOUTH);					
						movievec = new ItemCollections(movie);
						bookvec = new ItemCollections(book);				
						 // 모달 다이얼로그 추가
						MyModalDialog dialog = new MyModalDialog(this, "입력");
						// 추가 버튼에 액션리스너 추가
					        add2.addActionListener(new ActionListener() { 				        	
					              @Override
					        	public void actionPerformed(ActionEvent e) { 
					        	dialog.setVisible(true); // 모달 다이얼로그 보이도록 설정
					           // 선택된 라이오 버튼에 따라 모달다이얼로그의 내용 가져와 Item 객체 생성 후 vector에 추가
					        	if(dialog.getInputbRadioB() == "Movie") {			  
						        	String movi[];
						        	movi = dialog.getInputMovie();					        	
						        	// Movie 객체 만들기
						            Movie k = new Movie();
						            //정보 가져와 Item 객체에 정보 넣기
						            k.set(movi[0], movi[1], movi[3], movi[4], movi[5], dialog.getInputdate(), dialog.getInputscor(), 1, dialog.getyearmIndex(), dialog.getInputscorm2());
						            k.setMovie(movi[2], dialog.getInputSort(), dialog.getInputage(), dialog.getsortIndex(), dialog.getagecIndex());
						            // total, movie 벡터에 추가 후 각 리스트 리셋	      
						        	movievec.Iadd(k);				        			   
						        	total.add(k);				        	
						        	totalL.setListData(total);					        	
						        	movieL.setListData(movie);			            
					        	   }
					        	
						        if(dialog.getInputbRadioB() == "Book") {
						        	String boo[];
					        	boo = dialog.getInputBook();				        	
					            Book k2 = new Book();
					            //정보 가져와 Item 객체에 정보 넣기
					            k2.set(boo[0], boo[1], boo[3], boo[4], boo[5], dialog.getInputbdate(), dialog.getInputscor2(), 2, dialog.getyearbIndex(), dialog.getInputscorb2());
					            k2.setMovie(boo[2]);
					            Boolean bt = k2.equals("");				            
					            // total, movie 벡터에 추가 후 각 리스트 리셋	    
					            bookvec.Iadd(k2);		   
					        	total.add(k2);
					        	totalL.setListData(total);
					        	bookL.setListData(book);
						         }				        
						        
					              }	
					        	});				        
					        
						// 맨 위 팬널 추가				
						JPanel top = new JPanel();
						top.setLayout(new FlowLayout(FlowLayout.LEFT,330,0));					
						JLabel note = new JLabel("My Notes");
						// note 라벨 font 설정
						note.setFont(new Font("고딕", Font.ITALIC + Font.BOLD, 35));					
						note.setForeground(Color.BLUE);
						top.add(note);
						top.add(new MyTimeLabel());
						c.add(top, BorderLayout.NORTH);					
						
						// 상세 보기 팬널 작성
						p3 = new JPanel(); 
						
						Border resultBorder = BorderFactory.createTitledBorder("상세 보기");
						Border resultBorder2 = BorderFactory.createTitledBorder("줄거리");
						Border resultBorder3= BorderFactory.createTitledBorder("감상평");
						// 테투리 설정 
						JPanel whole = new JPanel();
						whole.setLayout(new BorderLayout(0,1));
						p3.setLayout(null);
						JPanel mid = new JPanel();
						JPanel review = new JPanel();
					    
					    grid = new GridLayout(7, 2);
					    grid.setVgap(5);				    
					    grid2 = new GridLayout(5, 2);
					  
					    imageLabel.setIcon(changeIconb); // 이미지 추가
			           	           
						mi = new JTextArea("", 9, 65);
						rev = new JTextArea("", 9, 65);
						// 각 textArea에 테두리, 스크롤 추가
						mid.setBorder(resultBorder2);
						mid.add(new JScrollPane(mi));					
						review.setBorder(resultBorder3);
						review.add(new JScrollPane(rev));
						
						imageLabel.setLocation(5,30); imageLabel.setSize(230, 275);
						
						mid.setLocation(5, 310); mid.setSize(780, 210);
						review.setLocation(5, 525);	review.setSize(780, 210);
						
					    p3.add(mid); p3.add(review);  p3.add(imageLabel);
				        // 각 요소 위치 설정 및 사이즈 설정 후 추가
						p3.setBorder(resultBorder);
						//테투리 펜널에 추가
						JPanel button2 = new JPanel();		
						JButton edit = new JButton("수정");
						JButton del = new JButton("삭제");
			            button2.add(edit);
			            button2.add(del);
			            
			            MyModalDialog dialog2 = new MyModalDialog(this, "입력");
					     // 모달 다이얼로그 추가			      
					       // 수정 버튼에 액션 리스너 추가  		            
			           edit.addActionListener(new ActionListener() { 			        	
				              @Override
				        	public void actionPerformed(ActionEvent e) { 		            	 
				            	
				            	  if(find.getDit() == 1) 
				            		  dialog2.setMovie(find);
				            	  if(find.getDit() == 2) 
				            		  dialog2.setbook(find);
				            		  			            	  
				            	  dialog2.setVisible(true); 
				            	  // 선택된 객체가 영화일 경우
				            	  if(find.getDit() == 1) {
				            		  
				            		  String movi[];
							        	movi = dialog2.getInputMovie();
							        
							        	// 이미지 아이콘 저장 
							        	int totalindex = total.indexOf(find);
							        	int moviindex = movie.indexOf(find);
							      
							        	// Movie 객체 만들기
							            Movie k = new Movie();
							            k.set(movi[0], movi[1], movi[3], movi[4], movi[5], dialog2.getInputdate(), dialog2.getInputscor(), 1, dialog2.getyearmIndex(), dialog2.getInputscorm2());
							            k.setMovie(movi[2], dialog2.getInputSort(), dialog2.getInputage(), dialog2.getsortIndex(), dialog2.getagecIndex());
							        
							            // 수정 결과 vector에 추가			      
							        	movievec.Iedit(moviindex, k);;				        			   
							        	total.set(totalindex, k);				        	
							        	totalL.setListData(total);					        	
							        	movieL.setListData(movie);	
							            
										p3.remove(pre); // p3  팬널에서 원래 상세 보기 팬널 제거
										JPanel now = new JPanel();
										dist2 = dePanel(k, now, dist2);	// 해당 항목의 결과과 수정된 팬널 p3에 추가 하는 함수
				            	  }
				            	// 선택된 객체가 책일 경우
				            	  if(find.getDit() == 2) {
				            		  String boo[];
							        	boo = dialog2.getInputBook();						   
							            Book k2 = new Book();
							            k2.set(boo[0], boo[1], boo[3], boo[4], boo[5], dialog2.getInputbdate(), dialog2.getInputscor2(), 2, dialog2.getyearbIndex(), dialog2.getInputscorb2());
							            k2.setMovie(boo[2]);
							            Boolean bt = k2.equals("");
							            int totalindex = total.indexOf(find);
							        	int booindex = book.indexOf(find);
							        	// 수정 결과 vector에 추가
							            bookvec.Iedit(booindex, k2);;				        			   
							        	total.set(totalindex, k2);				        	
							        	totalL.setListData(total);					        	
							        	bookL.setListData(book);	
							            
	                                    p3.remove(pre);   // p3  팬널에서 원래 상세 보기 팬널 제거
										JPanel now = new JPanel();   // 해당 항목의 결과과 수정된 팬널 p3에
										dist2 = dePanel(k2, now, dist2);
				            	  }
				            	  
				              }
				        	});
			        // 수정 버튼에 액션 리스너 추가  
			           del.addActionListener(new ActionListener() { 
				        	
				              @Override
				        	public void actionPerformed(ActionEvent e) { 
				            	  
				            	  int result = JOptionPane.showConfirmDialog(null,"정말 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
									if(result == JOptionPane.CLOSED_OPTION) // 사용자가 선택없이 다이얼로그 창을 닫은 경우
									{
									    	
									}
									else if(result == JOptionPane.YES_OPTION) // Yes를 누르면 종료
									{
										 if(dist2 == 1 && n != -1) { // 객체가 영화일 경우
							        		   total.remove(find);
							        		   movievec.Idelete(find);
							        		   totalL.setListData(total);
							        		   movieL.setListData(movie);
							        		   //각 벡터에서 객체 삭제 후 리셋 
							        		   mi.setText("");						        		   
							        		   rev.setText(""); 
							        		   imageLabel.setIcon(changeIconb);
							        		   p3.remove(pre);
							        		   // TextArea, 이미지 아이콘, 상세보기 팬널 p3 리셋
							        		   repaint();
							        		   
							        	   }
							        	   if(dist2 == 2 && n != -1) {// 객체가 영화일 경우
							        		   total.remove(find);
							        		   bookvec.Idelete(find);
							        		   totalL.setListData(total);
							        		   bookL.setListData(book);
							        		 //각 벡터에서 객체 삭제 후 리셋 
							        		   mi.setText("");
							        		   rev.setText("");
							        		   imageLabel.setIcon(changeIconb);
							        		   p3.remove(pre);
							        		// TextArea, 이미지 아이콘, 상세보기 팬널 p3 리셋
							        		   repaint();
							        	   } 	
									}
									else { // No를 선택한 경우
										
									}
				              }
				        	});
						
						whole.add(button2,BorderLayout.SOUTH);
						whole.add(p3, BorderLayout.CENTER);					
						
						c.add(east, BorderLayout.WEST); // 탭팬 추가
						c.add(whole, BorderLayout.CENTER);
						c.add(top, BorderLayout.NORTH);					
						
						setSize(1100, 900); // 프레임의 크기가 250 x 300
						setVisible(true);    // 프레임 출력
						
						
					}
					JPanel pre; // 전에 선택된 상세보기 패널		
					int dist2 = 0; // 전 패널 상채를 나타내는 변수
					int count = 0;
					Item find; 
					//리스트 항목 선택시 상세보기 패널 추가
					public class ListListener implements ListSelectionListener{
						 
		                 JList<Item> k;
						@Override
						public void valueChanged(ListSelectionEvent e) {
							k =(JList<Item>) e.getSource();
							Item ite = (Item) k.getSelectedValue();
						      int index = k.getSelectedIndex();					      
						      n = index;					     
						      if(k.getSelectedIndex() != -1) { // getSelectedIndex가 != -1 일 경우에만 
							
						    	  find = ite;	
							if(dist2 != 0) {
							p3.remove(pre);		
							}  // dist가 0 이 아니면 제거
							
							JPanel now = new JPanel();
							dist2 = dePanel(ite, now, dist2); // p3 팬널에 아이템 객체의 상세정보를 담은 now 팬널을 p3에 추가		
													
						      }
						}
					}
				   // 상세보기를 만드는 함수
					// p3 팬널에 아이템 객체의 상세정보를 담은 now 팬널을 p3에 추가
					public int dePanel(Item ite, JPanel now, int dist2) {
						Movie mt;
						Book bt;
					   if(ite.getDit() == 1) { // 객체가 영화일 경우
						   mt = (Movie)ite; 
						   String ml[] = mt.getAuthor();
						   // 객체의 여러 정보를 가져와 저장하기
						   String ml2[] = mt.getele();
						   
						   dist2 = 1;
						   mi.setText(ml[5]);
						   rev.setText(ml[6]);
						   
						   ico2 = new ImageIcon(ml[3]); // 이미지 아이콘 추가
						   Image img = ico2.getImage();
						    Image changeImage = img.getScaledInstance(230, 275, Image.SCALE_SMOOTH);
						    // 이미지 사이즈 변경 
						    ImageIcon changeIcon = new ImageIcon(changeImage);
						    // 이미지 아이콘에 아이콘 객체 
						   imageLabel.setIcon(changeIcon);	 
						   // TextArea에 줄거리와 리뷰 추가
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
								   infor2[i] = new JLabel(ml[2] + "년도");
							   else
								   infor2[i] = new JLabel(ml[4] + "점");
							   now.add(infor[i]);
				               now.add(infor2[i]);			               
					           
						   } // 팬널에 상세보기 Label 생성 및 추가
						   now.setLocation(245,30); now.setSize(200, 250);
						   p3.add(now);
						   pre = now;
						   // 위치 지정 후 p3 팬널에 now 팬널 추가
						   if(dist2 != 0)
							   repaint();
					   }   // 다시 그리기
					   // 책 객체가 선택 되었을 경우 
					   if(ite.getDit() == 2) {  
						   bt = (Book)ite;
						   // 객체의 여러 정보를 가져와 저장하기
						   String bl[] = bt.getAuthor();
						   String bl2 = bt.getPubli();
						   mi.setText(bl[5]);
						   rev.setText(bl[6]);
						   ico2 = new ImageIcon(bl[3]);
						   Image img = ico2.getImage();
						    Image changeImage = img.getScaledInstance(230, 275, Image.SCALE_SMOOTH);
						   // 이미지 사이즈 변경
						    ImageIcon changeIcon = new ImageIcon(changeImage);
						    // 이미지 아이콘에 아이콘 객체 
						   imageLabel.setIcon(changeIcon);
				            
						   now.setLayout(grid2);						  						   
						   dist2 = 2;
						   // now 팬널에 상세보기 라벨 추가
						   for(int i = 0; i < 5; i++) {
							  inforb[i] = new JLabel(inforsb[i]);
							   if(i == 2) {
								   inforb2[i] = new JLabel(bl2);
							   }
							   else if(i == 4) {
								   inforb2[i] = new JLabel(bl[4] + "점");
							   }
							   else if(i == 0 || i == 1) 
								   inforb2[i] = new JLabel(bl[i]);
							   
							   else
								   inforb2[i] = new JLabel(bl[2] + "년도");
							   now.add(inforb[i]);
				               now.add(inforb2[i]);			               
					           
						   }  // 팬널에 상세보기 Label 생성 및 추가 
						   now.setLocation(245,30); now.setSize(210, 250);
						   p3.add(now);
						   pre = now;												  						  
						
						   if(dist2 != 0)
							   repaint();
						// 위치 지정 후 p3 팬널에 now 팬널 추가
					   }				
					   return dist2;
						
					}
					
				// 시간을 나타내는 MyTimeLabel 함수
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
						// 각 날짜 및 시간 정보 가져오기						
						String date = year + "년 " + month + "월 " + day + "일 " + hour + ":" + min + ":" + second; 
						// date에 시간 정보 작성
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
					// 메뉴 생성 함수
					void creatMenu() { 
						JMenuItem [] mI = new JMenuItem[4];
						String[] itemT = {"불러오기", "저장하기", "종료", "시스템 정보"};
						JMenuBar mb = new JMenuBar(); // JMenuBar 컴포넌트 생성
						
						
						JMenu fileM = new JMenu("파일");
						JMenu helpM = new JMenu("도움말"); // JMenu 컴포넌트 생성
						// 메뉴 아이템 생성 후 JMenu에 붙이기
						for(int i = 0; i < 4; i++) {
							mI[i] = new JMenuItem(itemT[i]);
							if(i == 1) {
								fileM.add(mI[i]);
								fileM.addSeparator();  // 분리선 삽입
							}
							else if(i < 3) fileM.add(mI[i]);
							else helpM.add(mI[i]);
						}
						 						
						// 종료 메뉴에 확인 다이얼로그 추가
						mI[2].addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent e) {
							int result = JOptionPane.showConfirmDialog(null,"종료하시겠습니까?", "종료 확인", JOptionPane.YES_NO_OPTION);
							if(result == JOptionPane.CLOSED_OPTION) // 사용자가 선택없이 다이얼로그 창을 닫은 경우
							{
							    	
							}
							else if(result == JOptionPane.YES_OPTION) // Yes를 누르면 종료
							{
								System.exit (0);  	
							}
							else { // 아니오를 선택한 경우
							
							}
							}
						});				
				        
				     // 도움말 메뉴에 메시지 다이얼로그 추가				     	
				        mI[3].addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent e) {
								JOptionPane.showMessageDialog(null, "MyNotes 시스템 v 1.0 입니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
							}
						});
				        
				     // 파일 다이얼로그				        
				      // 열기 메뉴에 파일 열기 다이얼로그 추가
				        mI[0].addActionListener(new OpenActionListener()); 
				     // 저장 메뉴에 파일 열기 다이얼로그 추가
				        mI[1].addActionListener(new OpenActionListener()); 				
						mb.add(fileM);
						mb.add(helpM);      // 메뉴 바에 JMenu들 붙이기  						
						setJMenuBar(mb);
					}
					
					// 탭팬
					JTabbedPane ResultTabbedPane() {
						JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP);
						totalL.setVisibleRowCount(30); 
						totalL.setFixedCellWidth(280);
						searL.setVisibleRowCount(0); 
						searL.setFixedCellWidth(100); 
						search.setLayout(new BorderLayout());
						search.add(searL, BorderLayout.CENTER);
						creatToolBar(); // 툴바를 만드는 함수
						   // 탭 만들어 탭팬에 붙이기
							pane.addTab(tp[0], totalL); 
							pane.addTab(tp[1], movieL);
							pane.addTab(tp[2], bookL);
							pane.addTab(tp[3], search);						
						return pane;
					}
					
					void creatToolBar() { // 검색 리스트의 툴바 만들기
						JToolBar toolBar = new JToolBar("search");
						toolBar.setBackground(Color.LIGHT_GRAY);
						Vector<Item> searchv = new Vector<Item>();
						JComboBox<String> searSelect = new JComboBox<String>(); // 검색 탭에 붙일 콤보박스 생성
						searSelect.addItem("제목");
						searSelect.addItem("별점");    // 콤보박스에 요소 추가
						JTextField searT = new JTextField(12);
						JPanel sbutton = new JPanel();
						JButton searB = new JButton("검색");	// 툴바 요소 객체 만들기
						 // 검색 버튼에 액션 리스너 추가
						searB.addActionListener(new ActionListener() { 
				        	
				              @Override
				        	public void actionPerformed(ActionEvent e) { 				        
				            	  String stext = searT.getText(); 
				            	  String sselect = searSelect.getSelectedItem().toString();
				            	  Vector<Item> searchv = new Vector<Item>(); 			            	  
				            	  Item stotal;
				            	  int coun = 0;				            	  
				            	  // 제목일 경우 검색
				            	  if(sselect.equals("제목")) {		          		  
				            		  			            			  
				            			  for(int i = 0; i < total.size(); i++) {
				            				  stotal = total.get(i);
				            				  // 제목이 검색 문자열을 포함 할 경우
				            				  if(stotal.toString().contains(stext)) {
				            					  searchv.add(stotal); // 벡터에 추가
				            					  coun++;
				            				  }			            					  
				            		  }
				            			  searL.setListData(searchv); 
				            			  if(coun == 0) { // 검색 결과가 없는 경우 
				            				  JOptionPane.showMessageDialog(null, "[" + stext + "]" +" 검색 결과가 없습니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
				            			  }
				            	  }
				            	  // 별점으로 검색
				            	  if(sselect.equals("별점")) {
				            	  int k = Integer.parseInt(stext);
				            	  for(int i = 0; i < total.size(); i++) {
		            				  stotal = total.get(i);
		            				// 검색어 이상의 항목일 경우
		            				  if(stotal.getscor() >= k) {
		            					  searchv.add(stotal); // 벡터에 추가
		            					  coun++;
		            				  }			            					  
		            		  }
				            	  searL.setListData(searchv); 
				            	  if(coun == 0) { // 검색 결과가 없는 경우 
		            				  JOptionPane.showMessageDialog(null, " 검색 결과가 없습니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
		            			  }
				            	  }				            	  
				              }						          	
				        	});
						
						toolBar.add(searSelect);
						toolBar.add(searT);
						sbutton.add(searB);
						toolBar.add(sbutton);  // toolBar에 요소 추가
						search.add(toolBar, BorderLayout.NORTH);
						// toolBar 위쪽에 추가 
					}
					
					class OpenActionListener implements ActionListener { 
						
						private JFileChooser chooser = new JFileChooser(); 
						
						public void actionPerformed(ActionEvent e) { 
							
							String cmd = e.getActionCommand();	
							if(cmd.equals("불러오기")) {
								int f = chooser.showOpenDialog(null); 
								// 불러오기 다이얼로그 출력
								if(f != JFileChooser.APPROVE_OPTION) { 
									JOptionPane.showMessageDialog(null, "파일을  선택하지  않았습니다", "경고", JOptionPane.WARNING_MESSAGE); 
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
								} // 사용자가 선택한 파일 이름 알아내 라벨 내용 변경
							}
							
							if(cmd.equals("저장하기")) {
								int f = chooser.showOpenDialog(null); 
								// 저장하기 다이얼로그 출력
								if(f != JFileChooser.APPROVE_OPTION) { 
									JOptionPane.showMessageDialog(null, "파일을  선택하지  않았습니다", "경고", 
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
									
								} // 사용자가 선택한 파일 이름 알아내 라벨 내용 변경
							}
								
							}
							}
				
				public void savefile(String patName) throws Exception{
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("저장.dat")); // 파일 열기
					// 파일 열고 연결
					oos.writeObject(total);  // 파일에 컬렉션 저장
					oos.close();				
				}
				
				public void file(String patName) throws Exception{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("저장.dat")); 
	        	// 파일 열고 연결
	        	total = (Vector<Item>)ois.readObject();
	        	// total에 객체 저장
			    ois.close(); // 파일 닫기
			    totalL.setListData(total);  // totalL 리스트 리셋			    
			    
			    for(int i = 0; i < total.size(); i++) {
			    	
			    	if((total.get(i).getDit()) == 1)
			    		movievec.Iadd(total.get(i)); // 영화일 경우 movie 벡터에 Item 추가
			    	else
			    		bookvec.Iadd(total.get(i));// 책일 경우 book 벡터에 Item 추가
			    	
			    	 movieL.setListData(movie);	
			    	 bookL.setListData(book);	// 리스트 리셋	
			    }	        
				}
				
					public static void main(String[] args) throws Exception{
					
						new FinalProject();
				
					}
				}
				
				
