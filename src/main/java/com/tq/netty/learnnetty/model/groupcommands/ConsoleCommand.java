package com.tq.netty.learnnetty.model.groupcommands;

import io.netty.channel.Channel;

import java.util.Scanner;

public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);
}
