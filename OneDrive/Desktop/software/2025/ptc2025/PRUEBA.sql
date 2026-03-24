//Seccion de Usuario

CREATE TABLE TB_Rol
(
 ID_Rol           NUMBER PRIMARY KEY,
 Name_Rol         VARCHAR(200) NOT NULL
);

CREATE TABLE TB_User
(
 Email             VARCHAR(300) PRIMARY KEY,
 Password          VARCHAR(500) NOT  NULL,
 ID_Rol            NUMBER,
 CONSTRAINT FK_ID_Rol_User FOREIGN KEY (ID_Rol) REFERENCES TB_Rol(ID_Rol)
)

//Seccion de Empleado
CREATE TABLE TB_Employee
(
 NIT_Employee       VARCHAR(10) PRIMARY KEY,
 FirstName          VARCHAR(200) NOT NULL,
 LastName           VARCHAR(200) NOT NULL,
 BirthDay           DATE NOT NULL,
 Email              VARCHAR(200) NOT NULL,
 CONSTRAINT FK_Email_Employee FOREIGN KEY(Email) REFERENCES TB_User(Email)    
)

//SECCION NOTIFICACIONES
CREATE TABLE TB_Notifications
(
 ID_Notification     NUMBER PRIMARY KEY,
 Title               VARCHAR(200) NOT NULL,
 Message             VARCHAR(800) NOT NULL,
 Viewed              VARCHAR2(5) CHECK (UPPER(Viewed) IN ('TRUE', 'FALSE'))  NOT NULL,
 DT_Viewed           DATE NOT NULL,
 Type                VARCHAR(200),
 Email               VARCHAR(200),
 CONSTRAINT FK_Email_TB_Notifications FOREIGN KEY(Email) REFERENCES TB_User(Email)    
)

//Seccion De Observaciones

CREATE TABLE TB_Observation
(
 ID_Observation       NUMBER PRIMARY KEY,
 Name_Observation     VARCHAR(200) NOT NULL
)

CREATE TABLE TB_Observation_Category
(
 ID_Observation_Category   NUMBER PRIMARY KEY,
 Name_ObservationCategory  VARCHAR(500) NOT NULL,
 Description               VARCHAR(1000) 
)

CREATE TABLE TB_Observation_Employee
(
 ID_Observation_Employee  NUMBER PRIMARY KEY,
 DT_Report                DATE NOT NULL,
 ID_Observation           NUMBER NOT NULL,
 CONSTRAINT FK_ID_Observation_Observation_Employee FOREIGN KEY (ID_Observation) REFERENCES TB_Observation(ID_Observation),
 NIT_Employee             VARCHAR(10) NOT NULL,
 CONSTRAINT FK_NITEmployee_Observation_Employee FOREIGN KEY(NIT_Employee) REFERENCES TB_Employee(NIT_Employee),
 ID_Observation_Category  NUMBER NOT NULL,
 CONSTRAINT FK_ID_Observation_Category_Observation_Category FOREIGN KEY (ID_Observation_Category) REFERENCES TB_Observation_Category(ID_Observation_Category)
)


CREATE TABLE TB_Observation_Report
(
 ID_Observation_Report   NUMBER PRIMARY KEY,
 Date_Report             DATE NOT NULL,
 Description             VARCHAR(1000) NOT NULL,
 ID_Observation_Employee NUMBER,
 CONSTRAINT FK_ID_Observation_Employee_Observation_Report FOREIGN KEY (ID_Observation_Employee) REFERENCES TB_Observation_Employee(ID_Observation_Employee)
)

//Seccion de zonas y ubicaciones
CREATE TABLE TB_Zone
(
 ID_Zone                  NUMBER PRIMARY KEY,
 Name_zone                VARCHAR(200)
)

CREATE TABLE TB_Category
(
 ID_Category               NUMBER PRIMARY KEY,
 Name_Category             VARCHAR(200)
)

CREATE TABLE TB_Location
(
 ID_Location                VARCHAR(100) PRIMARY KEY,
 Name_Location              VARCHAR(200) NOT NULL,
 ID_Category                NUMBER NOT NULL,
 CONSTRAINT FK_ID_Category_TB_Location FOREIGN KEY (ID_Category) REFERENCES TB_Category(ID_Category),
 ID_Zone                    NUMBER NOT NULL,
 CONSTRAINT FK_ID_Zone_TB_Location FOREIGN KEY (ID_Zone) REFERENCES TB_Zone(ID_Zone)
)

//SECCION DE IMPLEMENTOS

CREATE TABLE TB_Extinguisher
(
 ID_Extinguisher             VARCHAR(50)PRIMARY KEY,
 Type_Extinguisher           VARCHAR(200) NOT NULL,
 Img_Extinguisher            BLOB,
 Expiration_Date             DATE NOT NULL,
 ID_Location                 VARCHAR(100) NOT NULL,
 CONSTRAINT FK_ID_Location_TB_Extinguisher FOREIGN KEY (ID_Location) REFERENCES TB_Location(ID_Location)
)

CREATE TABLE TB_SmokeDetector (
  ID_SmokeDetector     VARCHAR2(100) PRIMARY KEY,
  Type_Detector        VARCHAR2(200) NOT NULL,
  Expiration_Date      DATE NOT NULL,
  Energy_Source        VARCHAR2(150) NOT NULL,
  Installation_Date    DATE NOT NULL,
  IMG_Detector         BLOB,
  ID_Location           VARCHAR(100),
  CONSTRAINT FK_ID_Location_TB_SmokeDetector FOREIGN KEY (ID_Location) REFERENCES TB_Location(ID_Location)
)

//SECCION DE COMITE SSO
CREATE TABLE TB_Post
(
 ID_Post                NUMBER PRIMARY KEY,
 Name_Post              VARCHAR(150)
)

INSERT ALL 
INTO TB_Post(ID_Post, Name_Post) VALUES (1, 'Encargado_Inspeccion')
INTO TB_Post(ID_Post, Name_Post) VALUES (2, 'Envargado_Inspeccion_Accidentes')
SELECT * FROM Dual;

CREATE TABLE TB_CommitteMember
(
 ID_Member               NUMBER PRIMARY KEY,
 Name_Member             VARCHAR(200),
 ID_Post                 NUMBER NOT NULL,
 CONSTRAINT FK_IDPost_CommitteMember FOREIGN KEY(ID_Post) REFERENCES TB_Post(ID_Post)
)


//SECCION DE INSPECCIONES
CREATE TABLE TB_Inspection
(
 ID_Inspection          NUMBER PRIMARY KEY,
 Date_Inspection        DATE NOT NULL,
 ID_Member              NUMBER,
 ID_Location            VARCHAR(100),
 CONSTRAINT FK_ID_Location_Inspection FOREIGN KEY(ID_Location) REFERENCES TB_Location(ID_Location),
 State_Inspection       VARCHAR2(10) CHECK (UPPER(State_Inspection) IN ('COMPLETADO', 'PENDIENTE', 'VENCIDO')),
 CONSTRAINT FK_IDMember_Inspection FOREIGN KEY(ID_Member) REFERENCES TB_CommitteMember(ID_Member)
)

CREATE TABLE Type_Implements
(
 ID_TypeImplement      INT PRIMARY KEY,
 Name_Type             VARCHAR(100)
)

//SECCION DE ASIGNACIONES
CREATE TABLE TBAssign_inspection
(
  ID_Assing              NUMBER PRIMARY KEY,
  ID_Inspection          NUMBER,
  CONSTRAINT FK_ID_Inspection_Inspection FOREIGN KEY(ID_Inspection) REFERENCES TB_Inspection(ID_Inspection),
  ID_Implement           VARCHAR(100),
  ID_TypeImplement       INT, 
  CONSTRAINT FK_ID_TypeImplement_Inspection FOREIGN KEY(ID_TypeImplement) REFERENCES Type_Implements(ID_TypeImplement)
)

CREATE TABLE TBAssing_Location
(
 ID_Assign             NUMBER PRIMARY KEY,
 ID_Inspection         NUMBER,
 CONSTRAINT FK_ID_Inspection_InspectionLocation FOREIGN KEY(ID_Inspection) REFERENCES TB_Inspection(ID_Inspection),
 ID_Location            VARCHAR(100),
 CONSTRAINT FK_ID_Location_InspectionLocation FOREIGN KEY(ID_Location) REFERENCES TB_Location(ID_Location)
)

//SECCION DE DETALLES INSPECCIONES
CREATE TABLE TB_DetailInspection_Extinguisher
(
 ID_Extinguisher_Inspection NUMBER PRIMARY KEY,
 Extinguisher_InOperation   VARCHAR2(5) CHECK (UPPER(Extinguisher_InOperation) IN ('TRUE', 'FALSE'))  NOT NULL,
 Observation                VARCHAR(1000) NOT NULL,
 ID_Assing                  NUMBER,
 CONSTRAINT FK_ID_Assing_DetailExtinguisherInspection FOREIGN KEY(ID_Assing) REFERENCES TBAssign_inspection(ID_Assing)
)

CREATE TABLE TB_DetailLocation_Inspection
(
 ID_DetailLocation_Inspection NUMBER PRIMARY KEY,
 Visible_Signage              VARCHAR2(5) CHECK (UPPER(Visible_Signage) IN ('TRUE', 'FALSE'))  NOT NULL,
 Correct_Routes               VARCHAR2(5) CHECK (UPPER(Correct_Routes) IN ('TRUE', 'FALSE'))  NOT NULL,
 Free_EmergencyExits          VARCHAR2(5) CHECK (UPPER(Free_EmergencyExits) IN ('TRUE', 'FALSE'))  NOT NULL,
 Cleanfacilities              VARCHAR2(5) CHECK (UPPER(Cleanfacilities) IN ('TRUE', 'FALSE'))  NOT NULL,
 Observation                  VARCHAR(1000),
 ID_Assing                  NUMBER,
 CONSTRAINT FK_ID_Assing_DetailLocationInspection2 FOREIGN KEY(ID_Assing) REFERENCES TBAssign_inspection(ID_Assing)
)

CREATE TABLE TB_DetailInspection_Detector
(
 ID_InspectionDetector       NUMBER PRIMARY KEY,
 Battery_Status              VARCHAR(100) NOT NULL,
 LED_Indicator               VARCHAR(200) NOT NULL,
 Damaged_Marterial           VARCHAR2(5) CHECK (UPPER(Damaged_Marterial) IN ('TRUE', 'FALSE'))  NOT NULL,
 Observation                 VARCHAR(1000) NOT NULL,
 ID_Assing                   NUMBER,
 CONSTRAINT FK_ID_Assing_DetailLocationInspection FOREIGN KEY(ID_Assing) REFERENCES TBAssign_inspection(ID_Assing)
)

//SECCION DE BRIGADAS
CREATE TABLE TB_Brigade
(
 ID_Brigade          NUMBER PRIMARY KEY,
 Name_Brigade        VARCHAR(200) NOT NULL,
 Capacity_Staff      INT NOT NULL,
 IMG_Brigade         BLOB,
 Description         VARCHAR(1000) NOT NULL
)

CREATE TABLE TB_Employee_Brigade
(
 ID_Employee_Brigade   NUMBER PRIMARY KEY,
 NIT_Employee         VARCHAR(10)NOT NULL,
 CONSTRAINT FK_NITEmployee_EmployeeBrigade FOREIGN KEY(NIT_Employee) REFERENCES TB_Employee(NIT_Employee),
 ID_Brigade           NUMBER NOT NULL,
 CONSTRAINT FK_IDBrigade_EmployeeBrigade FOREIGN KEY(ID_Brigade) REFERENCES TB_Brigade(ID_Brigade)
)

CREATE TABLE TB_Training
(
 ID_Training          NUMBER PRIMARY KEY,
 Name_Training        VARCHAR(200)NOT NULL,
 Date_Training        DATE NOT NULL,
 IMG_Training         BLOB,
 Description          VARCHAR(1000) NOT NULL
)

CREATE TABLE TB_Brigades_Training
(
 ID_Brigade_Training   NUMBER PRIMARY KEY,
 ID_Brigade            NUMBER NOT NULL,
 CONSTRAINT FK_IDBrigade_BrigadesTraining FOREIGN KEY(ID_Brigade) REFERENCES TB_Brigade(ID_Brigade),
 ID_Training            NUMBER NOT NULL,
 CONSTRAINT FK_IDTraining_BrigadesTraining FOREIGN KEY(ID_Training) REFERENCES TB_Training(ID_Training)
)


//Seccion de accidentes
 CREATE TABLE TB_Emergency_Report
 (
  ID_EmergencyReport     NUMBER PRIMARY KEY,
  DT_ReportAccident      DATE NOT NULL,
  UserReporter           VARCHAR(200),
  State_Emergency        VARCHAR2(5) CHECK (UPPER(State_Emergency) IN ('TRUE', 'FALSE'))  NOT NULL,
  ID_Zone                NUMBER NOT NULL,
  CONSTRAINT FK_ID_Zone_TB_Emergency_Report FOREIGN KEY(ID_Zone) REFERENCES TB_Zone(ID_Zone)
 )
 
 CREATE TABLE TB_Accident
 (
  ID_Accident              NUMBER PRIMARY KEY,
  Confirmed_by             VARCHAR(200) NOT NULL,
  Date_Register            DATE NOT NULL,
  ID_EmergencyReport       NUMBER NOT NULL,
  CONSTRAINT FK_ID_EmergencyReport_TB_Accident FOREIGN KEY(ID_EmergencyReport) REFERENCES TB_Emergency_Report(ID_EmergencyReport)
 )

 CREATE TABLE TB_Category_Accident
 (
  ID_Category_Accident       NUMBER PRIMARY KEY,
  Name_Category              VARCHAR(200),
  Description                VARCHAR(500)
 )
 
 CREATE TABLE TB_Accident_Details
 (
  ID_Accident_Detail         NUMBER PRIMARY KEY,
  DT_Accident                DATE NOT NULL,
  Activity                   VARCHAR(500) NOT NULL,
  Witness                    VARCHAR(300) NOT NULL,
  Type_Injury                VARCHAR(200) NOT NULL,
  Injury_Part                VARCHAR(200) NOT NULL,
  Description                VARCHAR(1000)NOT NULL,
  ID_Category_Accident       NUMBER NOT NULL,
  CONSTRAINT  FK_ID_Category_Accident_TB_Accident_Details FOREIGN KEY (ID_Category_Accident) REFERENCES TB_Category_Accident(ID_Category_Accident),
  ID_Accident                NUMBER NOT NULL,
  CONSTRAINT FK_ID_Accident_TB_Accident_Details FOREIGN KEY (ID_Accident) REFERENCES TB_Accident(ID_Accident),
  ID_Member                  NUMBER NOT NULL,
  CONSTRAINT FK_IDMember_TB_Accident_Details FOREIGN KEY(ID_Member) REFERENCES TB_CommitteMember(ID_Member)
 )
 
 CREATE TABLE TB_Accident_Employes
 (
  ID_Employee_Accident        NUMBER PRIMARY KEY,
  NIT_Employee                VARCHAR(10) NOT NULL,
  CONSTRAINT FK_NIT_Employee__Accident_Employes FOREIGN KEY( NIT_Employee) REFERENCES TB_Employee(NIT_Employee),
  ID_Accident_Detail           NUMBER NOT NULL,
  CONSTRAINT FK_ID_Accident_Detail_Accident_Details FOREIGN KEY (ID_Accident_Detail) REFERENCES TB_Accident_Details(ID_Accident_Detail)
 );

//SECCION DE SIMULACROS
CREATE TABLE TB_Simulacrum 
(
  ID_Simulacrum              NUMBER PRIMARY KEY,
  Name_Simulacrum            VARCHAR(200) NOT NULL,
  IMG_Simulacrum             BLOB,
  DT_Simulacrum              DATE NOT NULL,
  ID_Member                  NUMBER NOT NULL,
  Simulacrum_Status          VARCHAR2(10) CHECK (UPPER(Simulacrum_Status) IN ('COMPLETADO', 'PENDIENTE', 'VENCIDO')),
  CONSTRAINT FK_IDMember_Simulacrum FOREIGN KEY(ID_Member) REFERENCES TB_CommitteMember(ID_Member)             
)

CREATE TABLE TB_Category_Protocol
(
 ID_Category                 NUMBER PRIMARY KEY,
 Name_Category               VARCHAR(200) NOT NULL
)

CREATE TABLE TB_Protocol
(
 ID_Protocol                  NUMBER PRIMARY KEY,
 Name_Protocol                VARCHAR(200),
 Description                  VARCHAR(1000),
 State_Protocol               VARCHAR2(20) CHECK (UPPER(State_Protocol) IN ('FUNCIONANDO', 'PENDIENTE', 'NOFUNCIONANDO')),
 ID_Protocol_Creator          NUMBER NOT NULL,
 CONSTRAINT FK_IDMember_TB_Protocol FOREIGN KEY(ID_Protocol_Creator) REFERENCES TB_CommitteMember(ID_Member),
 ID_Category                  NUMBER NOT NULL,
 CONSTRAINT FK_ID_Category_TB_Protocol FOREIGN KEY(ID_Category) REFERENCES TB_Category_Protocol(ID_Category)
)

CREATE TABLE TB_SimulacrumProtocol
(
 ID_SimulacrumProtocol        NUMBER PRIMARY KEY,
 ID_Simulacrum                NUMBER NOT NULL,
 CONSTRAINT FK_IDSimulacrum_SimulacrumProtocol  FOREIGN KEY(ID_Simulacrum) REFERENCES TB_Simulacrum(ID_Simulacrum),
 ID_Protocol                  NUMBER NOT NULL,
 CONSTRAINT FK_IDProtocol_SimulacrumProtocol  FOREIGN KEY(ID_Protocol) REFERENCES TB_Protocol(ID_Protocol)
)

CREATE TABLE TB_Report_Simulacrum
(
 ID_ReportSimulacrum           NUMBER PRIMARY KEY,
 DT_Evacuation                 DATE NOT NULL,
 Successful_Alert              VARCHAR2(5) CHECK (UPPER(Successful_Alert) IN ('TRUE', 'FALSE'))  NOT NULL,
 Deficiency                    VARCHAR2(5) CHECK (UPPER(Deficiency) IN ('TRUE', 'FALSE'))  NOT NULL,
 Participation_Evaluation      VARCHAR(200) NOT NULL,
 Observation                   VARCHAR(1000) NOT NULL,
 ID_Member_Reporter            NUMBER NOT NULL,
 CONSTRAINT FK_ID_Member_Reporter_TB_Report_Simulacrum FOREIGN KEY(ID_Member_Reporter) REFERENCES TB_CommitteMember(ID_Member)
)

 //Seccion de Mantenimientos

 CREATE TABLE TB_Maintenance
 (
  ID_Maintenace                 VARCHAR(200) PRIMARY KEY,
  Date_Maintenance              DATE NOT NULL,
  State_Maintenace              VARCHAR2(10) CHECK (UPPER(State_Maintenace) IN ('COMPLETADO', 'PENDIENTE', 'VENCIDO')),
  ID_Member                     NUMBER NOT NULL,
  CONSTRAINT FK_ID_Member_Reporter_TB_Maintenance FOREIGN KEY(ID_Member) REFERENCES TB_CommitteMember(ID_Member),
  ID_Location                   VARCHAR(100),
  CONSTRAINT FK_ID_Location_TB_Maintenance FOREIGN KEY(ID_Location) REFERENCES TB_Location(ID_Location)
 )
 
 CREATE TABLE TB_Assign_Maintence_Implements 
 (
  ID_Assing_Maintence           NUMBER PRIMARY KEY,
  ID_Implement                  VARCHAR2(100) NOT NULL,
  ID_TypeImplement              INT NOT NULL,
  CONSTRAINT FK_ID_TypeImplement_TB_Assign_Maintence_Implements FOREIGN KEY(ID_TypeImplement) REFERENCES Type_Implements(ID_TypeImplement),
  ID_Maintenace                 VARCHAR(200) NOT NULL,
  CONSTRAINT FK_ID_Maintenace_TB_Assign_Maintence_Implement FOREIGN KEY(ID_Maintenace) REFERENCES TB_Maintenance(ID_Maintenace)
 )
 
 CREATE TABLE TB_Type_Maintence
 (
 ID_Type_Maintence              NUMBER PRIMARY KEY,
 Name_Type                      VARCHAR(200) NOT NULL,
 Description                    VARCHAR(800)
 )
 
 CREATE TABLE TB_Detail_Maintence_Implements
 (
 ID_Detail_Maintence          NUMBER PRIMARY KEY,
 Name_Maintence               VARCHAR(200) NOT NULL,
 Total_Time                   DATE NOT NULL,
 Description                  VARCHAR(1000),
 Activity                     VARCHAR(200) NOT NULL,
 Img_Maintence                BLOB, 
 ID_Assing_Maintence          NUMBER NOT NULL,
 CONSTRAINT FK_ID_Assing_Maintence_TB_Detail_Maintence_Implements FOREIGN KEY (ID_Assing_Maintence) REFERENCES TB_Assign_Maintence_Implements(ID_Assing_Maintence),
 ID_Type_Maintence            NUMBER NOT NULL,
 CONSTRAINT FK_ID_Type_Maintence_TB_Detail_Maintence_Implements FOREIGN KEY (ID_Type_Maintence) REFERENCES TB_Type_Maintence(ID_Type_Maintence)
 )