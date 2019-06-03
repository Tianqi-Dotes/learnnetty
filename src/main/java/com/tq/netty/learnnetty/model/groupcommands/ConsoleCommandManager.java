package com.tq.netty.learnnetty.model.groupcommands;

import com.tq.netty.learnnetty.model.groupcommands.ConsoleCommand;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand {

    private Map<String,ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager(){
        consoleCommandMap=new HashMap<>();
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupCommand());
        consoleCommandMap.put("leaveGroup", new LeaveGroupCommand());

        consoleCommandMap.put("checkGroup", new GroupStatusCommand());
        consoleCommandMap.put("sendMsg", new SendMsgCommand());

    }

    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.println("login success! type in commands!");
        String command=scanner.next();

        ConsoleCommand consoleCommand=consoleCommandMap.get(command);

        if(consoleCommand!=null){
            consoleCommand.exec(scanner,channel);
        }else{
            System.out.println("command could not be resolved!");
        }
    }
}
