package com.tw.view;

public class PrintReportPage {

    public static String view = "请输入要打印的学生的学号(格式: 学号, 学号,...), 按回车提交:";
    public static String failed = "请按正确的格式输入要打印的学生的学号(格式: 学号, 学号,...), 按回车提交:";
    public static String success = "\n成绩单\n"
            + "姓名|数学|语文|英语|编程|平均分|总分\n"
            + "========================\n"
            + "%s"
            + "========================\n"
            + "全班总平均分：%.3f\n"
            + "全班总分中位数：%.3f\n";

}
