package fitnesstracker.view;

import fitnesstracker.model.DatabaseManager;
import fitnesstracker.model.Exercise;
import fitnesstracker.model.WorkoutPlan;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ExerciseBrowserView extends JPanel {
    private DatabaseManager dbManager;
    private JList<Exercise> exerciseList;
    private DefaultListModel<Exercise> listModel;
    private JTextArea descriptionArea;
    private JLabel muscleGroupLabel;
    private JButton addToWorkoutButton;
    private WorkoutSessionView workoutSessionView;
    
    public ExerciseBrowserView(WorkoutSessionView sessionView) {
        this.workoutSessionView = sessionView;
        this.dbManager = new DatabaseManager();
        initComponents();
        layoutComponents();
        registerListeners();
        loadExercises();
    }
    
    private void initComponents() {
        listModel = new DefaultListModel<>();
        exerciseList = new JList<>(listModel);
        exerciseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        exerciseList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Exercise exercise = (Exercise) value;
                setText(exercise.getName());
                return this;
            }
        });
        
        descriptionArea = new JTextArea(5, 30);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        
        muscleGroupLabel = new JLabel("Muscle Group: ");
        addToWorkoutButton = new JButton("Add to Current Workout");
        addToWorkoutButton.setEnabled(false);
    }
    
    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));
        
        // Exercise list panel
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Available Exercises"));
        listPanel.add(new JScrollPane(exerciseList), BorderLayout.CENTER);
        
        // Details panel
        JPanel detailsPanel = new JPanel(new BorderLayout(5, 5));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Exercise Details"));
        
        JPanel muscleGroupPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        muscleGroupPanel.add(muscleGroupLabel);
        
        detailsPanel.add(muscleGroupPanel, BorderLayout.NORTH);
        detailsPanel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addToWorkoutButton);
        
        // Add components to main panel
        add(listPanel, BorderLayout.WEST);
        add(detailsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    
    private void registerListeners() {
        exerciseList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Exercise selected = exerciseList.getSelectedValue();
                    if (selected != null) {
                        descriptionArea.setText(selected.getDescription());
                        muscleGroupLabel.setText("Muscle Group: " + selected.getMuscleGroup());
                        addToWorkoutButton.setEnabled(true);
                    } else {
                        descriptionArea.setText("");
                        muscleGroupLabel.setText("Muscle Group: ");
                        addToWorkoutButton.setEnabled(false);
                    }
                }
            }
        });
        
        addToWorkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exercise selected = exerciseList.getSelectedValue();
                if (selected != null && workoutSessionView != null) {
                    WorkoutPlan currentPlan = workoutSessionView.getSession().getWorkoutPlan();
                    // Add exercise to the current workout plan
                    currentPlan.addExercise(selected);
                    workoutSessionView.refreshExerciseList();
                    JOptionPane.showMessageDialog(ExerciseBrowserView.this, 
                        "Added '" + selected.getName() + "' to your current workout plan.",
                        "Exercise Added", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    
    private void loadExercises() {
        listModel.clear();
        List<Exercise> exercises = dbManager.getAllExercises();
        for (Exercise exercise : exercises) {
            listModel.addElement(exercise);
        }
    }
    
    // Call this when closing the application
    public void cleanup() {
        if (dbManager != null) {
            dbManager.closeConnection();
        }
    }
}