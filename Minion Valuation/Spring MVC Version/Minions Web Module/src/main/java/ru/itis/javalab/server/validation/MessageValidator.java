package ru.itis.javalab.server.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MessageValidator implements ConstraintValidator<ValidMessage, String> {

    public enum Strings {
        A("Doin a bamboozle fren."),
        B("I need help, teach me how to play!"),
        C("Let's be friends instead of fighting okay?"),
        D("I had something to say, then I forgot it."),
        E("ILY<3"),
        F("I enjoy long walks on the beach and playing Hypixel"),
        G("Hello everyone! I'm an innocent player who loves everything Hypixel."),
        H("Can you paint with all the colors of the wind"),
        I("When I saw the guy with a potion I knew there was trouble brewing."),
        J("Sometimes I sing soppy love songs in the car."),
        K("Blue is greener than purple for sure"),
        L("What happens if I add chocolate milk to macaroni and cheese?"),
        M("Anybody else really like Rick Astley?"),
        N("You're a great person! Do you want to play some Hypixel games with me?"),
        O("I love the way your hair glistens in the light."),
        P("In my free time I like to watch cat videos on youtube"),
        Q("Wait... This isn't what I typed!"),
        R("If the world in Minecraft is infinite....how can the sun revolve around it?"),
        S("When nothing is right, go left."),
        T("I need help, teach me how to play!"),
        U("Hey Helper, how play game?"),
        V("Your personality shines brighter than the sun."),
        W("Behold, the great and powerful, my magnificent and almighty nemesis!"),
        X("Please go easy on me, this is my first game!"),
        Y("Your Clicks per second are godly."),
        Z("I sometimes try to say bad things and then this happens :("),
        AA("I like pineapple on my pizza"),
        AB("Why can't the Ender Dragon read a book? Because he always starts at the End."),
        AC("I heard you like minecraft, so I built a computer so you can minecraft, while minecrafting in your minecraft."),
        AD("You are very good at this game friend."),
        AE("Pineapple doesn't go on pizza!"),
        AF("I have really enjoyed playing with you! <3"),
        AG("I like Minecraft pvp but you are truly better than me!"),
        AH("Pls give me doggo memes!"),
        AI("Maybe we can have a rematch?"),
        AJ("I like to eat pasta, do you prefer nachos?"),
        AK("With great power... comes a great electricity bill!");

        private final String message;

        Strings(final String message) {
            this.message = message;
        }

        private static final List<Strings> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Strings randomLetter()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }


    @Override
    public boolean isValid(String message, ConstraintValidatorContext constraintValidatorContext) {
        if ((message.toLowerCase().matches(".* ez .*"))
                ||(message.toLowerCase().matches("ez .*"))
                ||(message.toLowerCase().matches(".* ez]"))){
            return false;
        }
        return true;
    }
}
