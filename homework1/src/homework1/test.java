package homework1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;


public class test {

	public static int ms;  //�ڴ��С
	public static int ys;  //ҳ�Ĵ�С
	public static int pn;  //������
	public static int pd;  //�����ڶ���
	public static int dy;  //����ҳ�ĸ���
	
	 /**
	  * ���������ļ�
	  */
	public void init() {
		Properties params=new Properties();
		String configFile = "memory.properties";//�����ļ�·��
		//���������ļ�����������
		InputStream is=test.class.getClassLoader().getResourceAsStream(configFile);
		
		try {
			//���������ж�ȡ�����б�
			params.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//����ָ���Ļ�ȡ��Ӧ��ֵ
		ms=Integer.valueOf(params.getProperty("memorysize"));
		ys=Integer.valueOf(params.getProperty("yesize"));
		pn=Integer.valueOf(params.getProperty("progressnumber"));
		pd=Integer.valueOf(params.getProperty("progressduan"));
		dy=Integer.valueOf(params.getProperty("duanyenum"));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int pid;  //���̱��
		int  duanid;  //�߼���ַ�Ķκ�
		int pianyi; //����ƫ������
		int yst ;  //������̺��߼���ַ��Ӧ��ҳ��ʼַ
		test t = new test();
		t.init();
		Scanner scanner = new Scanner(System.in);
		System.out.print("���뷢������Ľ��̵ı��(��0��ʼ):");
		pid = scanner.nextInt();
		System.out.println("---------���뷢��������߼���ַ-----------");
		System.out.print("�κţ�");
		duanid = scanner.nextInt();
		System.out.println("����ƫ����:");
		pianyi = scanner.nextInt();
		
		//��ʼ���α�Ĵ���
		duantable dt= new duantable(ms/ys/dy);
		
		//������ý��̵Ķα�Χ
		int a = (pid)* pd;     //���ߣ����ڸý���ǰ��Ľ��̵�ռ�öγ���
		int b = (pid+1) *pd;    //���ߣ�������ֵԽ���ж�
		
		//�鿴�Ƿ�κ�Խ��
		if(duanid<=a||duanid>=b) {
			System.out.println("���棡���κ�Խ��,�����ַʧ��");
			return ;
		}else {
			//����ҳ��ʼַ
			yst = (duanid+dt.getStart_address()) * ys * dy;
		}
		
		//ͨ������ƫ�Ƽ��������ҳ�ź�ҳ�ڵ�ַ
		int dny = pianyi / ys;
		int ynd = pianyi % ys;
		
		//�鿴�Ƿ�ҳԽ��
		if(dny >= dy) {
			System.out.println("���棡��ҳ��Խ��,�����ַʧ��");
			return ;
		} 
		
		//���������ַ���
		int blockNo = dny + yst;
		System.out.println("�ɹ����뵽BlockNoΪ"+blockNo+",���ڵ�ַΪ:"+ynd+"�������ַ");
		
	}

}
