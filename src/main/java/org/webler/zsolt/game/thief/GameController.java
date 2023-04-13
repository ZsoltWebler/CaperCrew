package org.webler.zsolt.game.thief;

import org.webler.zsolt.game.thief.command.BadCommand;
import org.webler.zsolt.game.thief.command.Command;
import org.webler.zsolt.game.thief.exceptions.ThiefFactoryException;
import org.webler.zsolt.game.thief.phases.GamePhase;
import org.webler.zsolt.game.thief.phases.HeistPhase;
import org.webler.zsolt.game.thief.phases.MenuPhase;
import org.webler.zsolt.game.thief.skill.Mastermind;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameController {

    private final Mastermind mastermind;
    private GamePhase currentPhase;
    private final ThiefFactorySingleton thiefFactory = ThiefFactorySingleton.getInstance();
    private final HeistFactorySingleton heistFactory = HeistFactorySingleton.getInstance();


    public GameController(Mastermind mastermind) throws ThiefFactoryException {
        this.mastermind = mastermind;
        initResources(mastermind);
    }

    private void initResources(Mastermind mastermind) throws ThiefFactoryException {
        mastermind.setAvailableThieves(thiefFactory.getRandomThieves(10));
        mastermind.setSelectedCrew(thiefFactory.getRandomThieves(3));
        mastermind.getSelectedCrew().get(0).addRole(Role.DRIVER);
        mastermind.getSelectedCrew().get(1).addRole(Role.DRIVER);
        mastermind.getSelectedCrew().get(2).addRole(Role.GUNMAN);
        mastermind.setCurrentHeist(heistFactory.getHeist());
    }

    public void startGame() {
        printWelcomeMessage();
        switchToMenuPhase();
        playPhase();
    }

    private void playPhase() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            Map<String, Command> commandMap = currentPhase.getAvailableCommands().stream().collect(Collectors.toMap(Command::getCommandString, Function.identity()));
            String input = sc.nextLine();
            String command = input.split(" ")[0];

            commandMap.getOrDefault(command, new BadCommand()).execute(mastermind, parseArguments(input));
        }
    }

    private Optional<String> parseArguments(String input) {

        if (input.contains(" ")) {
            String arg = input.split(" ", 2)[1];
            if (!arg.trim().isEmpty()) {
                return Optional.of(arg);
            }
        }
        return Optional.ofNullable(null);


    }


    private void printWelcomeMessage() {
        System.out.println("Az lesz a feladatod, hogy összegyűjts " + mastermind.getMoneyToCollect()
                + "$-t " + mastermind.getNumberOfHeist() + " rablás alatt, különben meghalsz!");
    }

    public void switchToGamePhase() {
        currentPhase = new HeistPhase(this, mastermind);
    }

    public void switchToMenuPhase() {
        currentPhase = new MenuPhase(this, mastermind);
    }

    /*

    public void startGame() {



        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();

            switch (input) {

                case "/info":
                    info();
                    break;
                case "/menu":
                    menu();
                    break;
                case "/characters":
                    characterPool();
                    break;
                case "/new_characters":
                    newCharacterPool();
                    break;
                case "/heist":
                    heist();
                    break;
                case "/new_heist":
                    newHeist();
                    break;
                case "/start_heist":
                    startHeist();
                    break;

                default:
                    System.out.println("'" + input + "'" + " nem egy valid parancs!");
                    break;

            }

        }

    }







    private void addThiefIfExist(List<Thief> thieves, Optional<Thief> thief) {
        if (thief.isPresent()) {
            thieves.add(thief.get());
            System.out.println(thief.get().getName() + " hozza lett adva a csapathoz!");
        } else {
            System.out.println("Nincs ilyen karakter!");
        }
    }

    private void removeThiefIfExist(List<Thief> thieves, Optional<Thief> thief) {
        if (thief.isPresent()) {
            if (thieves.remove(thief.get())) {
                System.out.println(thief.get().getName() + " el lett távolítva a csapatból!");
            } else {
                System.out.println(thief.get().getName() + " nem tagja a csapatnak!");
            }

        } else {
            System.out.println("Nincs ilyen karakter!");
        }
    }

    private void assignThiefIfExist(List<Thief> thieves, Optional<Thief> thief) {
        if (thief.isPresent()) {
            if (thieves.contains(thief.get())) {
                System.out.println("Adja meg a role-t!");
                Scanner sc = new Scanner(System.in);
                String assigned_role = sc.nextLine();
                try {
                    Role role = Role.valueOf(assigned_role);
                    thief.get().addRole(role);
                    System.out.println(thief);
                } catch (IllegalArgumentException exception) {
                    System.out.println("Ilyen role nincs!");
                }
            } else {
                System.out.println(thief.get().getName() + " nem tagja a csapatnak!");
            }

        } else {
            System.out.println("Nincs ilyen karakter!");
        }
    }

    private void startHeist() {
        List<Thief> selectedThieves = new ArrayList<>();
        System.out.println(nextHeist);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();


            switch (input) {
                case "/add_thief":
                    System.out.println("Kérem adja meg a tolvaj nevét!");
                    addThiefIfExist(selectedThieves, selectThief(sc.nextLine()));
                    System.out.println(selectedThieves);
                    break;

                case "/remove_thief":
                    System.out.println("Kérem adja meg a tolvaj nevét!");
                    removeThiefIfExist(selectedThieves, selectThief(sc.nextLine()));
                    System.out.println(selectedThieves);
                    break;
                case "/assign":
                    System.out.println("Kérem adja meg a tolvaj nevét!");
                    assignThiefIfExist(selectedThieves, selectThief(sc.nextLine()));
                    break;
                case "/start":
                    doHeist(selectedThieves, nextHeist);
                    break;
                case "/characters":
                    characterPool();
                    break;
                case "/new_characters":
                    newCharacterPool();
                    break;
                case "/heist":
                    heist();
                    break;
            }
        }

    }

    public void doHeist(List<Thief> selectedThieves, Heist nextHeist) {

        if(selectedThieves != null){

        }

        List<Trial> trials = nextHeist.getTrials();
        Map<SkillType, List<Thief>> thievesBySkillType = new HashMap<>();

        for (SkillType skill : SkillType.values()) {
            for (Thief thief : selectedThieves) {
                for (Role role : thief.getRoles()) {
                    if (role.skills.contains(skill)) {
                        List<Thief> thieves = thievesBySkillType.get(skill);
                        if (thieves != null) {
                            List<Thief> newThieves = new ArrayList<>(thieves);
                            if(!newThieves.contains(thief)){
                                newThieves.add(thief);
                                thievesBySkillType.put(skill, newThieves);
                            }
                        } else {
                            thievesBySkillType.put(skill, Arrays.asList(thief));
                        }

                    }
                }
            }
        }

        for (Trial trial : trials) {
            System.out.println("A következő próba: " + trial);
            List<Thief> thieves = thievesBySkillType.get(trial.getType());
            boolean success = trial.difficulty < thieves.stream().mapToInt(thief -> thief.getSkills().get(trial.type)).sum();

            if (success) {
                System.out.println("\tSikeres próba");
            } else {
                System.out.println("\tSikertelen próba");
            }

        }

    }


    private void heist() {
        System.out.println(nextHeist);
    }

    private void characterPool() {
        characterPool.forEach(System.out::println);
    }

    private void info() {
        System.out.println("Összeg: " + mastermind.getCurrentMoney());
        System.out.println("Elhasznált lehetőségek: " + this.heistNumber);
        System.out.println("Hiányzó összeg: " + (mastermind.getMoneyToCollect() - mastermind.getCurrentMoney()));
    }

    private List<Thief> thiefGenerator() {

        List<Thief> thieves = new ArrayList<>();
        List<Character> possibleNames = new ArrayList<>();
        List<Character> usedNames = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            possibleNames.add(((char) ('a' + i)));
        }

        Random rnd = new Random();


        while (thieves.size() < 10) {
            Character name = possibleNames.get(rnd.nextInt(possibleNames.size()));
            if (!usedNames.contains(name)) {
                Thief thief = new Thief("" + name, getSkills());
                thieves.add(thief);
                usedNames.add(name);
                possibleNames.remove(name);
            }

        }

        return thieves;

    }

    private Map<SkillType, Integer> getSkills() {
        Map<SkillType, Integer> skills = Arrays.stream(SkillType.values()).map(skillType -> {
            Random rnd = new Random();
            Map.Entry<SkillType, Integer> skill = new AbstractMap.SimpleEntry<SkillType, Integer>(skillType, rnd.nextInt(99) + 1);
            return skill;
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return skills;

    }

*/
}
