package de.moso.entity.logic;

import org.springframework.data.annotation.Id;


/**
 * Created by sandro on 04.05.15.
 */
public class LogicBrick {
    @Id
    private String id;

    private Condition input;
    private Activation output;

    public LogicBrick(Condition input, Activation output) {
        this.input = input;
        this.output = output;
    }

    public LogicBrick() {
    }

}

