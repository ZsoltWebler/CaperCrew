package org.webler.zsolt.game.thief;

import org.webler.zsolt.game.thief.skill.Mastermind;
import org.webler.zsolt.game.thief.skill.SkillType;

import java.util.*;
import java.util.stream.Collectors;

public class GameController {

    private final Mastermind mastermind;
    private int heistNumber = 0;
    private List<Thief> characterPool = new ArrayList();
    private Heist nextHeist;

    public GameController(Mastermind mastermind) {
        this.mastermind = mastermind;
        this.characterPool = thiefGenerator();
        this.nextHeist = new Heist();
        System.out.println();

    }

    public void startGame() {

        System.out.println("Az lesz a feladatod, hogy összegyűjts " + mastermind.getMoneyToCollect()
                + "$-t " + mastermind.getNumberOfHeist() + " rablás alatt, különben meghalsz!");

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

    private void menu() {
        List<String> commands = Arrays.asList("/info", "/characters", "/heist", "/new_heist");
        commands.forEach(System.out::println);
    }

    private void newCharacterPool() {
        this.characterPool = thiefGenerator();
        System.out.println("Új karakterek érkeztek, de elveszítetted az összegyűjtött pénzed 5%-t!");
        this.mastermind.setCurrentMoney((int) (this.mastermind.getCurrentMoney() * 0.95));

    }

    private void newHeist() {
        this.nextHeist = new Heist();
        this.heistNumber += 1;
        System.out.println("Elhasználtál egy lehetőséget, már csak " +
                (mastermind.getNumberOfHeist() - this.heistNumber) + " maradt!");
        heist();

    }


    private Optional<Thief> selectThief(String thiefName) {
        Optional<Thief> thief = characterPool.stream().filter(_thief -> _thief.getName().equals(thiefName)).findFirst();
        return thief;

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


}
