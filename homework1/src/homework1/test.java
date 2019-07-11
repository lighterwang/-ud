package homework1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;


public class test {

	public static int ms;  //内存大小
	public static int ys;  //页的大小
	public static int pn;  //进程数
	public static int pd;  //进程内段数
	public static int dy;  //段内页的个数
	
	 /**
	  * 读入配置文件
	  */
	public void init() {
		Properties params=new Properties();
		String configFile = "memory.properties";//配置文件路径
		//加载配置文件到输入流中
		InputStream is=test.class.getClassLoader().getResourceAsStream(configFile);
		
		try {
			//从输入流中读取属性列表
			params.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//根据指定的获取对应的值
		ms=Integer.valueOf(params.getProperty("memorysize"));
		ys=Integer.valueOf(params.getProperty("yesize"));
		pn=Integer.valueOf(params.getProperty("progressnumber"));
		pd=Integer.valueOf(params.getProperty("progressduan"));
		dy=Integer.valueOf(params.getProperty("duanyenum"));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int pid;  //进程编号
		int  duanid;  //逻辑地址的段号
		int pianyi; //段内偏移量。
		int yst ;  //输入进程和逻辑地址对应的页表始址
		test t = new test();
		t.init();
		Scanner scanner = new Scanner(System.in);
		System.out.print("输入发出情求的进程的编号(从0开始):");
		pid = scanner.nextInt();
		System.out.println("---------输入发出申请的逻辑地址-----------");
		System.out.print("段号：");
		duanid = scanner.nextInt();
		System.out.println("段内偏移量:");
		pianyi = scanner.nextInt();
		
		//初始化段表寄存器
		duantable dt= new duantable(ms/ys/dy);
		
		//计算出该进程的段表范围
		int a = (pid)* pd;     //下线，即在该进程前面的进程的占用段长度
		int b = (pid+1) *pd;    //上线，超过该值越界中断
		
		//查看是否段号越界
		if(duanid<=a||duanid>=b) {
			System.out.println("警告！！段号越界,申请地址失败");
			return ;
		}else {
			//计算页表始址
			yst = (duanid+dt.getStart_address()) * ys * dy;
		}
		
		//通过段内偏移计算出段内页号和页内地址
		int dny = pianyi / ys;
		int ynd = pianyi % ys;
		
		//查看是否页越界
		if(dny >= dy) {
			System.out.println("警告！！页号越界,申请地址失败");
			return ;
		} 
		
		//计算物理地址块号
		int blockNo = dny + yst;
		System.out.println("成功申请到BlockNo为"+blockNo+",块内地址为:"+ynd+"的物理地址");
		
	}

}
