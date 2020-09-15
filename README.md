# Advanced-Paint

The program uses partial MVC (Model, View and Controller) as a structure. The different design patterns have been used such as Factory, Strategy and others to implement the paint. The common shapes were designed and implemented. The shapes feedback (user can see as it draws) was also made possible. Many Java Libraries and Tools were used to implement features such as Whole Colour Panel, Resolution, Save/Open, Symmetry and Thickness for most of the shapes. The program advanced paint is developed as a team. The instructor provided with a skeleton of basic code, notably the scribble package which is used by the paint package.

## High Level Architecture:

**Model:**
To keep the code simple, a partial MVC (Model, View and Controller) was implemented. The Model captures enough information to re-draw the sketch of the user.
	
**View & Controller:**
The View Class captures the JFrame as well as containing all of its components.
The View components contain the main View.
Examples of view components are PaintPanel and ShapeChooserPanel.
	
The View Class and View Components all implement their controller. For example, the PaintPanel listens to its MouseEvents.
This is instead of separating the Controller from the View. The choice
was made to simplify the code, yet still, keep it modular and clear.

Each view component can see the containing View as well as the Model as appropriate. This means that each view component can speak to the other view components. An example of this is when the ShapeChooserPanel buttons are clicked, resulting in a mode change in the PaintPanel.
