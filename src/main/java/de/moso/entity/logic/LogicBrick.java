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

    public Condition getInput() {
        return input;
    }

    public void setInput(Condition input) {
        this.input = input;
    }

    public Activation getOutput() {
        return output;
    }

    public void setOutput(Activation output) {
        this.output = output;
    }
}

