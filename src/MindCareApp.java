import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class MindCareApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Patient> patients = new ArrayList<>();
    private static List<CognitiveGame> games = new ArrayList<>();
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {
        initializeGames();
        boolean running = true;

        while (running) {
            displayMainMenu();
            int choice = getUserChoice(1, 7);

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    viewPatients();
                    break;
                case 3:
                    playCognitiveGame();
                    break;
                case 4:
                    viewPatientProgress();
                    break;
                case 5:
                    setCognitiveReminder();
                    break;
                case 6:
                    viewPlatformInfo();
                    break;
                case 7:
                    running = false;
                    displayExitMessage();
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid option. Please try again." + ANSI_RESET);
            }
        }
        scanner.close();
    }

    private static void displayMainMenu() {
        clearScreen();
        System.out.println("\n" + ANSI_BOLD + ANSI_CYAN + "╔════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_CYAN + "║     🧠 MINDCARE - Cognitive Gaming Platform 🧠      ║" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_CYAN + "║   AI-Powered Elderly Dementia Support System        ║" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_CYAN + "╚════════════════════════════════════════════════════╝" + ANSI_RESET);

        System.out.println("\n" + ANSI_BOLD + "┌─ Main Menu ─────────────────────────────────────┐" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "  1. 👤 Register New Patient" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "  2. 📋 View All Patients" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "  3. 🎮 Play Cognitive Games" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "  4. 📊 View Patient Progress" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "  5. 🔔 Set Health Reminders" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "  6. ℹ️  Platform Information" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "  7. 🚪 Exit Application" + ANSI_RESET);
        System.out.println(ANSI_BOLD + "└──────────────────────────────────────────────────┘" + ANSI_RESET);
        System.out.print(ANSI_GREEN + "Select an option (1-7): " + ANSI_RESET);
    }

    private static void registerPatient() {
        clearScreen();
        System.out.println("\n" + ANSI_BOLD + ANSI_GREEN + "╔════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_GREEN + "║        👤 PATIENT REGISTRATION FORM                ║" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_GREEN + "╚════════════════════════════════════════════════════╝" + ANSI_RESET);

        try {
            System.out.print("\n" + ANSI_CYAN + "Enter Patient Name: " + ANSI_RESET);
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty");

            System.out.print(ANSI_CYAN + "Enter Patient Age: " + ANSI_RESET);
            int age = Integer.parseInt(scanner.nextLine());
            if (age < 50 || age > 120) throw new IllegalArgumentException("Age should be between 50-120");

            System.out.print(ANSI_CYAN + "Enter Gender (Male/Female/Other): " + ANSI_RESET);
            String gender = scanner.nextLine().trim();

            System.out.println(ANSI_CYAN + "\nSelect Health Condition:" + ANSI_RESET);
            String[] diseases = {"Dementia", "Alzheimer's Disease", "Parkinson's Disease", "Mild Cognitive Impairment", "Stroke Recovery", "Depression", "Anxiety", "Other"};
            for (int i = 0; i < diseases.length; i++) {
                System.out.println("  " + (i + 1) + ". " + diseases[i]);
            }
            System.out.print(ANSI_GREEN + "Select disease (1-8): " + ANSI_RESET);
            int diseaseChoice = Integer.parseInt(scanner.nextLine());
            String disease = diseases[diseaseChoice - 1];

            System.out.println(ANSI_CYAN + "\nSelect Condition Severity:" + ANSI_RESET);
            System.out.println("  1. Mild");
            System.out.println("  2. Moderate");
            System.out.println("  3. Severe");
            System.out.print(ANSI_GREEN + "Select severity (1-3): " + ANSI_RESET);
            int severityChoice = Integer.parseInt(scanner.nextLine());
            String[] severities = {"Mild", "Moderate", "Severe"};
            String severity = severities[severityChoice - 1];

            System.out.print(ANSI_CYAN + "Enter Email Address: " + ANSI_RESET);
            String email = scanner.nextLine().trim();

            System.out.print(ANSI_CYAN + "Enter Phone Number: " + ANSI_RESET);
            String phone = scanner.nextLine().trim();

            System.out.print(ANSI_CYAN + "Enter Caregiver Name (optional): " + ANSI_RESET);
            String caregiver = scanner.nextLine().trim();

            System.out.println(ANSI_CYAN + "\nSelect Region (NER):" + ANSI_RESET);
            String[] regions = {"Assam", "Arunachal Pradesh", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Sikkim", "Tripura"};
            for (int i = 0; i < regions.length; i++) {
                System.out.println("  " + (i + 1) + ". " + regions[i]);
            }
            System.out.print(ANSI_GREEN + "Select region (1-8): " + ANSI_RESET);
            int regionChoice = Integer.parseInt(scanner.nextLine());
            String region = regions[regionChoice - 1];

            Patient patient = new Patient(name, age, gender, disease, severity, email, phone, caregiver, region);
            patients.add(patient);

            System.out.println("\n" + ANSI_GREEN + "✓ Patient registered successfully!" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "  Patient ID: " + patient.getPatientId() + ANSI_RESET);
            pressEnterToContinue();

        } catch (Exception e) {
            System.out.println(ANSI_RED + "✗ Registration failed: " + e.getMessage() + ANSI_RESET);
            pressEnterToContinue();
        }
    }

    private static void viewPatients() {
        clearScreen();
        System.out.println("\n" + ANSI_BOLD + ANSI_BLUE + "╔════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_BLUE + "║           📋 REGISTERED PATIENTS                   ║" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_BLUE + "╚════════════════════════════════════════════════════╝" + ANSI_RESET);

        if (patients.isEmpty()) {
            System.out.println(ANSI_YELLOW + "\nNo patients registered yet." + ANSI_RESET);
        } else {
            System.out.println("\n" + ANSI_CYAN + String.format("%-10s %-20s %-5s %-20s %-15s", "Patient ID", "Name", "Age", "Disease", "Severity") + ANSI_RESET);
            System.out.println(ANSI_BOLD + "─".repeat(80) + ANSI_RESET);

            for (Patient patient : patients) {
                System.out.println(String.format("%-10s %-20s %-5d %-20s %-15s",
                        patient.getPatientId(),
                        patient.getName(),
                        patient.getAge(),
                        patient.getDisease(),
                        patient.getSeverity()));
            }
        }
        pressEnterToContinue();
    }

    private static void playCognitiveGame() {
        clearScreen();
        System.out.println("\n" + ANSI_BOLD + ANSI_GREEN + "╔════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_GREEN + "║        🎮 COGNITIVE GAMING INTERFACE               ║" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_GREEN + "╚════════════════════════════════════════════════════╝" + ANSI_RESET);

        if (patients.isEmpty()) {
            System.out.println(ANSI_RED + "\nPlease register a patient first." + ANSI_RESET);
            pressEnterToContinue();
            return;
        }

        System.out.println(ANSI_CYAN + "\nSelect a patient:" + ANSI_RESET);
        for (int i = 0; i < patients.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + patients.get(i).getName());
        }
        System.out.print(ANSI_GREEN + "Select patient (1-" + patients.size() + "): " + ANSI_RESET);
        int patientChoice = getUserChoice(1, patients.size()) - 1;
        Patient selectedPatient = patients.get(patientChoice);

        System.out.println("\n" + ANSI_CYAN + "Available Games:" + ANSI_RESET);
        for (int i = 0; i < games.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + games.get(i).getName());
        }
        System.out.print(ANSI_GREEN + "Select game (1-" + games.size() + "): " + ANSI_RESET);
        int gameChoice = getUserChoice(1, games.size()) - 1;
        CognitiveGame selectedGame = games.get(gameChoice);

        System.out.println("\n" + ANSI_BOLD + ANSI_YELLOW + "Starting: " + selectedGame.getName() + ANSI_RESET);
        playGame(selectedPatient, selectedGame);
    }

    private static void playGame(Patient patient, CognitiveGame game) {
        System.out.println("\n" + ANSI_GREEN + game.getDescription() + ANSI_RESET);
        
        int score = 0;
        Random random = new Random();

        if (game.getName().contains("Memory")) {
            score = playMemoryGame();
        } else if (game.getName().contains("Pattern")) {
            score = playPatternGame();
        } else if (game.getName().contains("Attention")) {
            score = playAttentionGame();
        }

        patient.addGameScore(game.getName(), score);
        System.out.println(ANSI_GREEN + "\n✓ Game completed! Score: " + score + "/100" + ANSI_RESET);
        pressEnterToContinue();
    }

    private static int playMemoryGame() {
        System.out.println(ANSI_CYAN + "\nMemory Game: Remember the sequence!" + ANSI_RESET);
        int[] sequence = {1, 3, 2, 4, 1, 3};
        System.out.print("Sequence: ");
        for (int num : sequence) {
            System.out.print(ANSI_YELLOW + num + " " + ANSI_RESET);
        }
        System.out.println("\nEnter the sequence (space-separated):");
        System.out.print(ANSI_GREEN + "> " + ANSI_RESET);
        
        String input = scanner.nextLine();
        String[] inputs = input.split(" ");
        
        int correct = 0;
        for (int i = 0; i < Math.min(inputs.length, sequence.length); i++) {
            try {
                if (Integer.parseInt(inputs[i]) == sequence[i]) {
                    correct++;
                }
            } catch (NumberFormatException e) {
                // Invalid input
            }
        }
        
        return (correct * 100) / sequence.length;
    }

    private static int playPatternGame() {
        System.out.println(ANSI_CYAN + "\nPattern Recognition Game: Identify the pattern!" + ANSI_RESET);
        System.out.println("Pattern: 2, 4, 6, 8, ?");
        System.out.println("Options: 1) 9  2) 10  3) 12  4) 14");
        System.out.print(ANSI_GREEN + "Your answer (1-4): " + ANSI_RESET);
        
        int answer = getUserChoice(1, 4);
        return answer == 2 ? 100 : 50;
    }

    private static int playAttentionGame() {
        System.out.println(ANSI_CYAN + "\nAttention Focus Game: Find the odd one out!" + ANSI_RESET);
        System.out.println("Items: Apple, Banana, Orange, Hammer");
        System.out.println("1) Apple  2) Banana  3) Orange  4) Hammer");
        System.out.print(ANSI_GREEN + "Your answer (1-4): " + ANSI_RESET);
        
        int answer = getUserChoice(1, 4);
        return answer == 4 ? 100 : 50;
    }

    private static void viewPatientProgress() {
        clearScreen();
        System.out.println("\n" + ANSI_BOLD + ANSI_BLUE + "╔════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_BLUE + "║           📊 PATIENT PROGRESS DASHBOARD             ║" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_BLUE + "╚════════════════════════════════════════════════════╝" + ANSI_RESET);

        if (patients.isEmpty()) {
            System.out.println(ANSI_RED + "\nNo patients registered yet." + ANSI_RESET);
            pressEnterToContinue();
            return;
        }

        System.out.println(ANSI_CYAN + "\nSelect a patient to view progress:" + ANSI_RESET);
        for (int i = 0; i < patients.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + patients.get(i).getName());
        }
        System.out.print(ANSI_GREEN + "Select patient (1-" + patients.size() + "): " + ANSI_RESET);
        int patientChoice = getUserChoice(1, patients.size()) - 1;
        Patient patient = patients.get(patientChoice);

        System.out.println("\n" + ANSI_GREEN + "Patient: " + patient.getName() + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Age: " + patient.getAge() + ", Condition: " + patient.getDisease() + ANSI_RESET);
        System.out.println("\n" + ANSI_CYAN + "Game Scores:" + ANSI_RESET);
        
        if (patient.getGameScores().isEmpty()) {
            System.out.println(ANSI_YELLOW + "No games played yet." + ANSI_RESET);
        } else {
            for (Map.Entry<String, Integer> entry : patient.getGameScores().entrySet()) {
                System.out.println("  " + ANSI_YELLOW + entry.getKey() + ": " + entry.getValue() + "/100" + ANSI_RESET);
            }
        }
        pressEnterToContinue();
    }

    private static void setCognitiveReminder() {
        clearScreen();
        System.out.println("\n" + ANSI_BOLD + ANSI_YELLOW + "╔════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "║        🔔 HEALTH REMINDER MANAGEMENT                ║" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_YELLOW + "╚════════════════════════════════════════════════════╝" + ANSI_RESET);

        if (patients.isEmpty()) {
            System.out.println(ANSI_RED + "\nPlease register a patient first." + ANSI_RESET);
            pressEnterToContinue();
            return;
        }

        System.out.println(ANSI_CYAN + "\nSelect a patient:" + ANSI_RESET);
        for (int i = 0; i < patients.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + patients.get(i).getName());
        }
        System.out.print(ANSI_GREEN + "Select patient (1-" + patients.size() + "): " + ANSI_RESET);
        int patientChoice = getUserChoice(1, patients.size()) - 1;
        Patient patient = patients.get(patientChoice);

        System.out.println("\n" + ANSI_CYAN + "Select Reminder Type:" + ANSI_RESET);
        System.out.println("  1. 💊 Medication Reminder");
        System.out.println("  2. 💧 Hydration Reminder");
        System.out.println("  3. 🏃 Exercise Reminder");
        System.out.println("  4. 🍽️  Meal Reminder");
        System.out.println("  5. 📅 Medical Appointment");
        System.out.print(ANSI_GREEN + "Select reminder type (1-5): " + ANSI_RESET);

        int reminderChoice = getUserChoice(1, 5);
        String[] reminders = {"Medication", "Hydration", "Exercise", "Meal", "Medical Appointment"};
        String reminderType = reminders[reminderChoice - 1];

        System.out.print(ANSI_CYAN + "Enter reminder time (HH:MM): " + ANSI_RESET);
        String time = scanner.nextLine();

        System.out.print(ANSI_CYAN + "Enter reminder details: " + ANSI_RESET);
        String details = scanner.nextLine();

        patient.addReminder(reminderType, time, details);
        System.out.println(ANSI_GREEN + "✓ Reminder set successfully!" + ANSI_RESET);
        pressEnterToContinue();
    }

    private static void viewPlatformInfo() {
        clearScreen();
        System.out.println("\n" + ANSI_BOLD + ANSI_CYAN + "╔════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_CYAN + "║        ℹ️  MINDCARE PLATFORM INFORMATION            ║" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_CYAN + "╚════════════════════════════════════════════════════╝" + ANSI_RESET);

        System.out.println(ANSI_GREEN + "\n🧠 About MindCare:" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "  MindCare is an AI-powered cognitive gaming platform");
        System.out.println("  designed specifically for elderly dementia patients in");
        System.out.println("  the North Eastern Region of India." + ANSI_RESET);

        System.out.println(ANSI_GREEN + "\n🎯 Key Features:" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "  • Interactive cognitive games for memory enhancement");
        System.out.println("  • AI-adapted difficulty levels based on patient performance");
        System.out.println("  • Multilingual and voice-assisted interface");
        System.out.println("  • Comprehensive caregiver monitoring dashboard");
        System.out.println("  • Smart health reminders and notifications");
        System.out.println("  • Offline functionality for low-connectivity areas");
        System.out.println("  • Simple and elderly-friendly user interface" + ANSI_RESET);

        System.out.println(ANSI_GREEN + "\n📞 Contact Information:" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "  Email: support@mindcare.health");
        System.out.println("  Phone: +91 1800-MINDCARE");
        System.out.println("  Address: Healthcare Center, North Eastern Region, India" + ANSI_RESET);

        System.out.println(ANSI_GREEN + "\n🌍 Supported Regions (NER):" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "  Assam, Arunachal Pradesh, Manipur, Meghalaya,");
        System.out.println("  Mizoram, Nagaland, Sikkim, Tripura" + ANSI_RESET);

        System.out.println(ANSI_GREEN + "\n📊 Platform Statistics:" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "  Total Patients Registered: " + patients.size());
        System.out.println("  Available Games: " + games.size());
        System.out.println("  Supported Languages: 8 (Regional + English)" + ANSI_RESET);

        pressEnterToContinue();
    }

    private static void initializeGames() {
        games.add(new CognitiveGame("Memory Challenge", "Test your memory by recalling sequences and patterns."));
        games.add(new CognitiveGame("Pattern Recognition", "Identify missing patterns and complete the sequences."));
        games.add(new CognitiveGame("Attention Focus", "Enhance your concentration with spot-the-difference games."));
        games.add(new CognitiveGame("Word Association", "Improve vocabulary and cognitive connections."));
        games.add(new CognitiveGame("Number Sequence", "Solve mathematical patterns and sequences."));
    }

    private static void displayExitMessage() {
        clearScreen();
        System.out.println("\n" + ANSI_BOLD + ANSI_GREEN + "╔════════════════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_GREEN + "║                   👋 THANK YOU!                     ║" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_GREEN + "║   MindCare - Supporting Elderly Cognitive Health    ║" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_GREEN + "╚════════════════════════════════════════════════════╝" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "\nExiting application... Have a great day!" + ANSI_RESET);
    }

    private static int getUserChoice(int min, int max) {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice >= min && choice <= max) {
                return choice;
            }
            System.out.print(ANSI_RED + "Invalid choice. Please enter a number between " + min + " and " + max + ": " + ANSI_RESET);
            return getUserChoice(min, max);
        } catch (NumberFormatException e) {
            System.out.print(ANSI_RED + "Invalid input. Please enter a number: " + ANSI_RESET);
            return getUserChoice(min, max);
        }
    }

    private static void pressEnterToContinue() {
        System.out.print(ANSI_BLUE + "\nPress Enter to continue..." + ANSI_RESET);
        scanner.nextLine();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Patient Class
    static class Patient {
        private String patientId;
        private String name;
        private int age;
        private String gender;
        private String disease;
        private String severity;
        private String email;
        private String phone;
        private String caregiver;
        private String region;
        private LocalDateTime registrationDate;
        private Map<String, Integer> gameScores;
        private List<Reminder> reminders;
        private static int patientCounter = 1000;

        public Patient(String name, int age, String gender, String disease, String severity,
                       String email, String phone, String caregiver, String region) {
            this.patientId = "PAT" + (++patientCounter);
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.disease = disease;
            this.severity = severity;
            this.email = email;
            this.phone = phone;
            this.caregiver = caregiver;
            this.region = region;
            this.registrationDate = LocalDateTime.now();
            this.gameScores = new HashMap<>();
            this.reminders = new ArrayList<>();
        }

        public void addGameScore(String gameName, int score) {
            gameScores.put(gameName, score);
        }

        public void addReminder(String type, String time, String details) {
            reminders.add(new Reminder(type, time, details));
        }

        // Getters
        public String getPatientId() { return patientId; }
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getGender() { return gender; }
        public String getDisease() { return disease; }
        public String getSeverity() { return severity; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getCaregiver() { return caregiver; }
        public String getRegion() { return region; }
        public LocalDateTime getRegistrationDate() { return registrationDate; }
        public Map<String, Integer> getGameScores() { return gameScores; }
        public List<Reminder> getReminders() { return reminders; }
    }

    // Reminder Class
    static class Reminder {
        private String type;
        private String time;
        private String details;
        private LocalDateTime createdAt;

        public Reminder(String type, String time, String details) {
            this.type = type;
            this.time = time;
            this.details = details;
            this.createdAt = LocalDateTime.now();
        }

        public String getType() { return type; }
        public String getTime() { return time; }
        public String getDetails() { return details; }
    }

    // Cognitive Game Class
    static class CognitiveGame {
        private String name;
        private String description;
        private int difficultyLevel;

        public CognitiveGame(String name, String description) {
            this.name = name;
            this.description = description;
            this.difficultyLevel = 1;
        }

        public String getName() { return name; }
        public String getDescription() { return description; }
        public int getDifficultyLevel() { return difficultyLevel; }
        public void setDifficultyLevel(int level) { this.difficultyLevel = level; }
    }
}
