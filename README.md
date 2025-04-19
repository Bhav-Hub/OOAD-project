"# Fitness Tracker Application\n" +
"\n" +
"A comprehensive workout and fitness tracking application built using Java Swing with MVC architecture. This application helps you manage workout plans, track exercise progress, calculate BMI, log calories, schedule workouts, and more.\n" +
"\n" +
"## Features\n" +
"\n" +
"- Create and manage workout plans  \n" +
"- Track exercise sets and reps during workout sessions  \n" +
"- View workout progress through visual graphs  \n" +
"- Calculate BMI and determine health category  \n" +
"- Log and track calorie intake  \n" +
"- Schedule workouts and set reminders  \n" +
"- Browse exercise library  \n" +
"- Dark and light mode themes  \n" +
"\n" +
"## Requirements\n" +
"\n" +
"- Java Development Kit (JDK) 8 or higher  \n" +
"- SQLite JDBC Driver (included in commands below)  \n" +
"\n" +
"## Getting Started\n" +
"\n" +
"### Clone the Repository\n" +
"\n" +
"```bash\n" +
"git clone https://github.com/yourusername/fitness-tracker.git\n" +
"cd OOAD-project\n" +
"```\n" +
"\n" +
"### Compile the Application\n" +
"\n" +
"```bash\n" +
"javac fitnesstracker/*.java fitnesstracker/model/*.java fitnesstracker/controller/*.java fitnesstracker/view/*.java\n" +
"```\n" +
"\n" +
"### Run the Application\n" +
"\n" +
"**For Windows:**\n" +
"\n" +
"```bash\n" +
"java -cp \"lib\\sqlite-jdbc-3.49.1.0.jar;.\" fitnesstracker.Main\n" +
"```\n" +
"\n" +
"**For Linux/Mac:**\n" +
"\n" +
"```bash\n" +
"java -cp \"lib/sqlite-jdbc-3.49.1.0.jar:.\" fitnesstracker.Main\n" +
"```\n" +
"\n" +
"> **Note:** Make sure to place the SQLite JDBC driver in a `lib` folder within your project directory. If the driver is located elsewhere, adjust the path accordingly.\n" +
"\n" +
"## Usage\n" +
"\n" +
"1. **Workout Plan Manager Tab** – Create and manage your workout plans  \n" +
"2. **Workout Session Tab** – Start a workout session and track your exercises  \n" +
"3. **Progress Graph Tab** – View your workout progress visually  \n" +
"4. **Reminders Tab** – Set reminders for your workouts  \n" +
"5. **Schedule Workout Tab** – Schedule your future workouts  \n" +
"6. **Calorie Log Tab** – Log and track your calorie intake  \n" +
"7. **BMI Calculator Tab** – Calculate your BMI and determine health category  \n" +
"8. **Exercise Library Tab** – Browse and add exercises to your workout plans  \n" +
"\n" +
"## Theme Toggle\n" +
"\n" +
"Click the **Dark Mode** or **Light Mode** button at the top right to switch between themes.\n" +
"\n" +
"## Additional Information\n" +
"\n" +
"- The application uses an SQLite database to store exercise information.  \n" +
"- The database is automatically created and populated with sample exercises when you first run the application.";
