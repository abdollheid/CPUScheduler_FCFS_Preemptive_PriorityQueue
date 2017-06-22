
package cpuscheduler;


import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene. Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class CPUscheduler extends Application {
    double rWidth , rHight ;
    int processN;
    boolean secondTime;
//    @Override
    public  void start(Stage app){
      
        if(secondTime){
             Pane pane = new Pane();
               Scene scene = new Scene (pane , Screen.getPrimary().getVisualBounds().getWidth()  , Screen.getPrimary().getVisualBounds().getHeight()); 
        pane.setStyle("-fx-background-color:white");
      
        
          
         
         
         
         
        
         rWidth =119*pane.getWidth()/120 ;
         rHight = pane.getHeight()/15 ; 
        
        Chart chartF= new Chart ((pane.getWidth()/2)-(rWidth/2),((5*pane.getHeight())/9)-(rHight/2),rWidth , rHight , pane);
          Chart chartP= new Chart ((pane.getWidth()/2)-(rWidth/2),((7*pane.getHeight())/8)-(rHight/2),rWidth , rHight , pane);
          
        
           chartF.setArcHeight(10);
        chartF.setArcWidth(10);
        chartP.setArcHeight(10);
        chartP.setArcWidth(10);
       
        Text ftext = new Text(7 ,((5*pane.getHeight())/11),"FCFS Gantt Chart:" );
        ftext.setStyle("-fx-fill:red ; -fx-font-size:30px");
                
      
        
       
        
         
//         Process process = new Process(4 , new int []{2,6,8,10} , new int []{47,49,50,29} ,new int []{6,6,6,8} ) ; 
         Process process = new Process(processN) ; 
         process.generate();
//           
         FCFS a =new FCFS(process,chartF);
        Text ftext2 = new Text(7 ,((6*pane.getHeight())/12),"Total waiting time: " +a.getTotalTime());
        ftext2.setStyle(" -fx-font-size:20px");
        Text ftext3 = new Text((pane.getWidth()/3) ,((6*pane.getHeight())/12),"Average time: " +(((int)(a.getAverageTime()*100))/100.0));
        ftext3.setStyle(" -fx-font-size:20px");
        
         PrimitivePriorityQueueScheduling f = new PrimitivePriorityQueueScheduling(process,chartP );
          
          Text ptext = new Text(7 ,((9*pane.getHeight())/12),"Priority Queue Gantt Chart:" );
        ptext.setStyle("-fx-fill:red ; -fx-font-size:30px");
       Text ptext2 = new Text(7 ,((12*pane.getHeight())/15),"Total waiting time: " +f.getWaitingTime());
        ptext2.setStyle(" -fx-font-size:20px");
        Text ptext3 = new Text((pane.getWidth()/3) ,((12*pane.getHeight())/15),"Average time: " +(((int)(f.getAverageTime()*100))/100.0));
        ptext3.setStyle(" -fx-font-size:20px");
               
      Image image = new Image("pic.PNG");
      ImageView imageView = new ImageView(image);
      imageView.setFitHeight(56);
      imageView.setFitWidth(66);
      
      if(a.getAverageTime()<f.getAverageTime()){
               imageView.setLayoutX(((8*pane.getWidth())/12));
      imageView.setLayoutY(((5*pane.getHeight())/12));
        
      }else{
               imageView.setLayoutX(((8*pane.getWidth())/12));
      imageView.setLayoutY(((11*pane.getHeight())/15));
      }
        
  
     ScrollPane sp = new ScrollPane();
  
     sp.setMinWidth(pane.getWidth());
     sp.setMinHeight(4*pane.getHeight()/11);
     sp.setMaxWidth(4*pane.getHeight()/11);
     sp.setHbarPolicy(ScrollBarPolicy.NEVER);
     sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
   
    sp.setPrefSize(pane.getWidth(), (4*pane.getHeight()/11));
    
   
    
     
     TableView<ProcessData> table = new TableView<>();
   
     table.setPrefWidth(pane.getWidth());
       TableColumn<ProcessData,Integer> processNumber = new TableColumn<>("Process");
       processNumber.setMinWidth(pane.getWidth()/4);
    processNumber.setCellValueFactory(new PropertyValueFactory<>("p"));
    processNumber.setStyle("-fx-font-size:20px; ");
 
     
       TableColumn <ProcessData,Integer>arivalTime = new TableColumn<>("Arrival Time");
     arivalTime.setMinWidth(pane.getWidth()/4);
       arivalTime.setCellValueFactory(new PropertyValueFactory<>("arrival"));
         arivalTime.setStyle("-fx-font-size:20px; ");
 
         
         TableColumn <ProcessData,Integer>priority = new TableColumn<>("Priority");
         priority.setMinWidth(pane.getWidth()/4);
       priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
           priority.setStyle("-fx-font-size:20px; ");
         
           TableColumn <ProcessData,Integer>burstTime = new TableColumn<>("Burst Time");
         burstTime.setMinWidth(pane.getWidth()/4);
           burstTime.setCellValueFactory(new PropertyValueFactory<>("burst"));
         burstTime.setStyle("-fx-font-size:20px; ");
         
         
         
       table.setItems(getTableData(process));
       table.getColumns().addAll(processNumber,arivalTime,priority,burstTime);
     
       sp.setContent(table);
     
       FlowPane cc = new FlowPane();
       cc.setAlignment(Pos.CENTER);
       Button again = new Button("Generate");
       TextField t1 = new TextField(this.processN+"");
//       t1.setLayoutX((11*pane.getWidth()/14));
//       t1.setLayoutY(5*pane.getHeight()/13);
       cc.setHgap(10);
       cc.getChildren().addAll(t1,again);
       
       cc.setLayoutY(5*pane.getHeight()/13);
       cc.setLayoutX(12*pane.getWidth()/16);
       again.setStyle("-fx-font-size:14px");
//       again.setLayoutX(13*pane.getWidth()/14);
//       again.setLayoutY(5*pane.getHeight()/13);
       again.setOnAction(e->{
           processN=new Integer(t1.getText());
   this.start(app);
       });
 

   
      pane.getChildren().addAll(sp,chartP,chartF,ftext,ftext2,ftext3, ptext,ptext2,ptext3,imageView,cc);
         app.setScene(scene);
        }else{
            secondTime=true;
              FlowPane pane = new FlowPane(Orientation.VERTICAL);
              pane.setAlignment(Pos.CENTER);
              pane.setVgap(15);
              Label sub = new Label("CPU Scheduler");
              sub.setStyle("-fx-font-size:53px;");
           
              FlowPane container = new FlowPane();
             container.setAlignment(Pos.BOTTOM_RIGHT);
              Label doc = new Label("Dr.Samir El Mongy");
              doc.setStyle("-fx-font-size:20px ; -fx-text-fill:red;");
              container.getChildren().add(doc);
              
              FlowPane head = new FlowPane();
              head.getChildren().addAll(sub,container);
              FlowPane.setMargin(head, new Insets(-150,0,80,0));
              
                     
              FlowPane container0= new FlowPane();
                container0.setAlignment(Pos.CENTER);
              Label doc0 = new Label("Credits:");
            doc0.setStyle("-fx-font-size:20px ; -fx-text-fill:black;");
              container0.getChildren().add(doc0);
              
              
              FlowPane container1 = new FlowPane();
                container1.setAlignment(Pos.CENTER);
              Label doc1 = new Label("Abdullah Eid");
            doc1.setStyle("-fx-font-size:20px ; -fx-text-fill:blue;");
              container1.getChildren().add(doc1);
              
               
              
         
              
              FlowPane bc= new FlowPane(Orientation.HORIZONTAL);
              TextField t =new TextField("3");
              
              FlowPane.setMargin(t, new Insets(0,12,-200,0));
              bc.getChildren().add(t);
              bc.setAlignment(Pos.CENTER);
              Button bl =new Button("Generate Process");
              bl.setOnAction(e->{
                  processN=new Integer(t.getText());
              this.start(app);
              });
              bc.getChildren().add(bl);
              bl.setStyle("-fx-font-size:17px");
              FlowPane.setMargin(bl, new Insets(0,0,-200,0));
      
            pane.getChildren().addAll(head,container0,container1,bc);
              Scene scene = new Scene (pane , Screen.getPrimary().getVisualBounds().getWidth()  , Screen.getPrimary().getVisualBounds().getHeight()); 
              
  
        app.setScene(scene);
        }
        app.setTitle("Cpu Scheduler");
        app.show();
        
    }
    
    public ObservableList<ProcessData> getTableData(Process p){
        ObservableList<ProcessData> data = FXCollections.observableArrayList();
       for(int i =0 ; i<p.size ; ++i){
           data.add(new ProcessData(i+1,p.ArrivalTime[i],p.Priority[i],p.BurstTime[i]));
       }
        return data;
        
    }

   
    public static void main(String[] args) {
   Application.launch(args);
        
//       Process p = new Process(3) ; 
//       p.generate(28);
//       PriorityQueue pq =new PriorityQueue(p);
//       FCFS f = new FCFS  (p);
//       System.out.println("Arrival time is : "+Arrays.toString(p.ArrivalTime));
//       System.out.println("Burst time is : "+Arrays.toString(p.BurstTime)) ; 
//       f.calculateTime(); 
//       System.out.println("the total time is :"+ f.getTotalWaitingTime());
//       System.out.println("average Time is :"+ f.getAverage());
        
    }
 
    
}