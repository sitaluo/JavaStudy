package com.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.WString;

public class PrintJnaUtil {
	public static final Integer GROUP_USUALLY = 0x10000                               ;
    public static final Integer GROUP_SETTING = 0x20000                               ;
    public static final Integer GROUP_HARDWARE = 0x40000                              ;
    public static final Integer GROUP_SYSTEM = 0x80000                                ;
    public static final Integer GROUP_FLSHPROG = 0x100000                             ;
    public static final Integer STATUS_ERROR = 0x80000000                             ;
    public static final Integer STATUS_USUALLY_IDLE = GROUP_USUALLY | 0x1           ;
    public static final Integer STATUS_USUALLY_PRINTING = GROUP_USUALLY | 0x2       ;
    public static final Integer STATUS_USUALLY_STANDSTILL = GROUP_USUALLY | 0x4     ;
    public static final Integer STATUS_USUALLY_PAPER_END = GROUP_USUALLY | 0x8      ;
    public static final Integer STATUS_USUALLY_RIBBON_END = GROUP_USUALLY | 0x10    ;
    public static final Integer STATUS_USUALLY_COOLING = GROUP_USUALLY | 0x20       ;
    public static final Integer STATUS_USUALLY_MOTCOOLING = GROUP_USUALLY | 0x40   ;
    public static final Integer STATUS_SETTING_COVER_OPEN = GROUP_SETTING | 0x1     ;
    public static final Integer STATUS_SETTING_PAPER_JAM = GROUP_SETTING | 0x2      ;
    public static final Integer STATUS_SETTING_RIBBON_ERR = GROUP_SETTING | 0x4     ;
    public static final Integer STATUS_SETTING_PAPER_ERR = GROUP_SETTING | 0x8      ;
    public static final Integer STATUS_SETTING_DATA_ERR = GROUP_SETTING | 0x10     ;
    public static final Integer STATUS_SETTING_SCRAPBOX_ERR = GROUP_SETTING | 0x20 ;
    public static final Integer STATUS_HARDWARE_ERR01 = GROUP_HARDWARE | 0x1       ;
    public static final Integer STATUS_HARDWARE_ERR02 = GROUP_HARDWARE | 0x2       ;
    public static final Integer STATUS_HARDWARE_ERR03 = GROUP_HARDWARE | 0x4       ;
    public static final Integer STATUS_HARDWARE_ERR04 = GROUP_HARDWARE | 0x8       ;
    public static final Integer STATUS_HARDWARE_ERR05 = GROUP_HARDWARE | 0x10      ;
    public static final Integer STATUS_HARDWARE_ERR06 = GROUP_HARDWARE | 0x20      ;
    public static final Integer STATUS_HARDWARE_ERR07 = GROUP_HARDWARE | 0x40      ;
    public static final Integer STATUS_HARDWARE_ERR08 = GROUP_HARDWARE | 0x80      ;
    public static final Integer STATUS_HARDWARE_ERR09 = GROUP_HARDWARE | 0x100     ;
    public static final Integer STATUS_HARDWARE_ERR10 = GROUP_HARDWARE | 0x200     ;
    
	public interface PrinterDllApi extends Library {                                 ;
		/**
		 * 当前路径是在项目下，而不是bin输出目录下。
		 */
		PrinterDllApi INSTANCE = (PrinterDllApi) Native.load("CyStat64", PrinterDllApi.class);

		public Integer PortInitialize(WString value);
		public Integer GetStatus(Integer port);
		public Integer GetCounterL(Integer port);
		public Integer GetCounterA(Integer port);
		public Integer SetClearCounterA(Integer port);
		
	}
	
	private static Integer port = -1;
	static {
		initPort();
	}
	private static void initPort() {
		port = PrinterDllApi.INSTANCE.PortInitialize(new WString("USB001"));
	}
	
	public static Integer getCounterL() {
		Integer counterL = PrinterDllApi.INSTANCE.GetCounterL(port);
		return counterL;
	}
	
	public static Integer getCounterA() {
		Integer counterA = PrinterDllApi.INSTANCE.GetCounterA(port);
		return counterA;
	}
	
	private static Integer getStatus() {
		Integer status = PrinterDllApi.INSTANCE.GetStatus(port);
		return status;
	}
	
	public static String getStatusStr() {
		Integer stat = getStatus();
		String statusStr = "";
		if (stat < 0) {
			statusStr = "ERROR";
		} else if (stat.equals(STATUS_USUALLY_IDLE)) {
			statusStr = "Idle";//空闲
		} else if (stat.equals(STATUS_USUALLY_PRINTING)) {
			statusStr = "Printing";
		} else if (stat.equals(STATUS_USUALLY_STANDSTILL)) {
			statusStr = "STANDSTILL";//停止
		} else if (stat.equals(STATUS_USUALLY_PAPER_END)) {
			statusStr = "Paper End";
		} else if (stat.equals(STATUS_USUALLY_RIBBON_END)) {
			statusStr = "Ribbon End";
		} else if (stat.equals(STATUS_USUALLY_COOLING)) {
			statusStr = "Head Cooling Down";
		} else if (stat.equals(STATUS_USUALLY_MOTCOOLING)) {
			statusStr = "Motor Cooling Down";//电机冷却
		}
		return statusStr;

	}
	

	public static void main(String[] args) {
		/*Integer port = PrinterDllApi.INSTANCE.PortInitialize(new WString("USB001"));
		Integer status = PrinterDllApi.INSTANCE.GetStatus(port);
		Integer GetCounterL = PrinterDllApi.INSTANCE.GetCounterL(port);
		Integer GetCounterA = PrinterDllApi.INSTANCE.GetCounterA(port);
		System.out.println("port:"+port);
		System.out.println("status:"+status);
		System.out.println("GetCounterL:"+GetCounterL);
		System.out.println("GetCounterA:"+GetCounterA);
		System.out.println("end");*/
		System.out.println(getStatusStr());
	}
}
