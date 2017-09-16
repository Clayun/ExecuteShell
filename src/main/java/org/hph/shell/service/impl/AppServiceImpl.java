package org.hph.shell.service.impl;

import org.hph.shell.model.XShell;
import org.hph.shell.service.AppService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * localhost:2344/XShell/run?path=/home/Mikesam/&name=print.sh
 */
@Service
public class AppServiceImpl implements AppService {

    @Override
    public List<String> runShell(XShell xShell) {
        Process process;
        List<String> line = new ArrayList<String>();
        String cmd = xShell.getPath() + xShell.getName();//这里必须要给文件赋权限 chmod u+x fileName;
        String permission = "chmod 777 " + cmd;
        try {
            // 使用Runtime来执行command，生成Process对象
            Runtime runtime = Runtime.getRuntime();
            if (xShell.getIs_root().equals("true")) {
                process = Runtime.getRuntime().exec(permission);
                process.waitFor();
            }
            process = runtime.exec(cmd);
            // 取得命令结果的输出流
            InputStream is = process.getInputStream();
            // 用一个读输出流类去读
            InputStreamReader isr = new InputStreamReader(is);
            // 用缓冲器读行
            BufferedReader br = new BufferedReader(isr);
            String lin = null;
            while ((lin = br.readLine()) != null) {
                System.out.println(">>>>>"+lin);
                line.add(lin);
            }
            //执行关闭操作
            is.close();
            isr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            return line;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return line;
        }
        return line;
    }
}
