/* USER CODE BEGIN Header */
/**
  ******************************************************************************
  * @file           : main.c
  * @brief          : Main program body
  ******************************************************************************
  * @attention
  *
  * <h2><center>&copy; Copyright (c) 2020 STMicroelectronics.
  * All rights reserved.</center></h2>
  *
  * This software component is licensed by ST under BSD 3-Clause license,
  * the "License"; You may not use this file except in compliance with the
  * License. You may obtain a copy of the License at:
  *                        opensource.org/licenses/BSD-3-Clause
  *
  ******************************************************************************
  */
/* USER CODE END Header */

/* Includes ------------------------------------------------------------------*/
#include "main.h"

/* Private includes ----------------------------------------------------------*/
/* USER CODE BEGIN Includes */

#define NOTE_C4  262   //Defining note frequency
#define NOTE_D4  294
#define NOTE_E4  330
#define NOTE_F4  349
#define NOTE_G4  392
#define NOTE_A4  440
#define NOTE_B4  494
#define NOTE_C5  523
#define NOTE_D5  587
#define NOTE_E5  659
#define NOTE_F5  698
#define NOTE_G5  784
#define NOTE_A5  880
#define NOTE_B5  988
/* USER CODE END Includes */

/* Private typedef -----------------------------------------------------------*/
/* USER CODE BEGIN PTD */

/* USER CODE END PTD */

/* Private define ------------------------------------------------------------*/
/* USER CODE BEGIN PD */
/* USER CODE END PD */

/* Private macro -------------------------------------------------------------*/
/* USER CODE BEGIN PM */

/* USER CODE END PM */

/* Private variables ---------------------------------------------------------*/
TIM_HandleTypeDef htim1;
TIM_HandleTypeDef htim2;
TIM_HandleTypeDef htim3;

UART_HandleTypeDef huart2;
UART_HandleTypeDef huart6;

/* USER CODE BEGIN PV */

int tim_it=0;
int nota_stack[10];
int last_index = 0;

int record_list[200];
int record_duraction[200];
int record_length = 0;
int r_start = 0;
int r_d = 0;
int previous = 0;


char rec[6];
int rxcmp = 0;
int i;

int bt = 0;
int stop = 0;

int songs[4] = {0,0,0,0};

/* USER CODE END PV */

/* Private function prototypes -----------------------------------------------*/
void SystemClock_Config(void);
static void MX_GPIO_Init(void);
static void MX_TIM1_Init(void);
static void MX_TIM2_Init(void);
static void MX_TIM3_Init(void);
static void MX_USART2_UART_Init(void);
static void MX_USART6_UART_Init(void);
/* USER CODE BEGIN PFP */

/* USER CODE END PFP */

/* Private user code ---------------------------------------------------------*/
/* USER CODE BEGIN 0 */
void set_rgb (uint8_t red, uint8_t green, uint8_t blue)
{
	htim1.Instance->CCR1 = red;
	htim1.Instance->CCR2 = green;
	htim1.Instance->CCR3 = blue;
}



int setfreq(int freq) // 1 to 3000 Hz
{
int period = 60000 / freq; // compute period as function of 60KHz ticks
TIM2->ARR = period - 1;
TIM2->CCR2 = period / 2; // Channel 2 50/50
TIM2->CNT = 0;
return 0;
}

int get_index(int i){

	for(int j=0;10>j;j++){
		if(i == nota_stack[j] )
			return j;
	}
	return 0;
}


void delay(int num){

	num = num * 9000;

	for(int i=0;num>i;i++){

	}
}


void play_rgb_notes(int note){
		switch(note){
		case 262:
			set_rgb(72,61,139);
			break;
		case  NOTE_D4:
			set_rgb(39,64,139);
			break;
		case  NOTE_E4:
			set_rgb(0,191,255);
			break;
		case  NOTE_F4:
			set_rgb(0,245,255);
			break;
		case  NOTE_G4:
			set_rgb(0,255,127);
			break;
		case  NOTE_A4:
			set_rgb(173,255,47);
			break;
		case  NOTE_B4:
			set_rgb(205,190,112);
			break;
		case  NOTE_C5:
			set_rgb(255,106,106);
			break;
		case  NOTE_D5:
			set_rgb(238,64,0);
			break;
		case  NOTE_E5:
			set_rgb(255,20,147);
			break;
		case  NOTE_F5:
			set_rgb(52,152,219);
			break;
		case  NOTE_G5:
			set_rgb(26,188,156);
			break;
		case  NOTE_A5:
			set_rgb(241,196,15);
			break;
		case  NOTE_B5:
			set_rgb(186,74,0);
			break;

		}
}

void play_pirates(){
	 float notes[] = {
	    NOTE_E4, NOTE_G4, NOTE_A4, NOTE_A4, 0,
	    NOTE_A4, NOTE_B4, NOTE_C5, NOTE_C5, 0,
	    NOTE_C5, NOTE_D5, NOTE_B4, NOTE_B4, 0,
	    NOTE_A4, NOTE_G4, NOTE_A4, 0,

	    NOTE_E4, NOTE_G4, NOTE_A4, NOTE_A4, 0,
	    NOTE_A4, NOTE_B4, NOTE_C5, NOTE_C5, 0,
	    NOTE_C5, NOTE_D5, NOTE_B4, NOTE_B4, 0,
	    NOTE_A4, NOTE_G4, NOTE_A4, 0,

	    NOTE_E4, NOTE_G4, NOTE_A4, NOTE_A4, 0,
	    NOTE_A4, NOTE_C5, NOTE_D5, NOTE_D5, 0,
	    NOTE_D5, NOTE_E5, NOTE_F5, NOTE_F5, 0,
	    NOTE_E5, NOTE_D5, NOTE_E5, NOTE_A4, 0,

	    NOTE_A4, NOTE_B4, NOTE_C5, NOTE_C5, 0,
	    NOTE_D5, NOTE_E5, NOTE_A4, 0,
	    NOTE_A4, NOTE_C5, NOTE_B4, NOTE_B4, 0,
	    NOTE_C5, NOTE_A4, NOTE_B4, 0,

	    NOTE_A4, NOTE_A4,
	    //Repeat of first part
	    NOTE_A4, NOTE_B4, NOTE_C5, NOTE_C5, 0,
	    NOTE_C5, NOTE_D5, NOTE_B4, NOTE_B4, 0,
	    NOTE_A4, NOTE_G4, NOTE_A4, 0,

	    NOTE_E4, NOTE_G4, NOTE_A4, NOTE_A4, 0,
	    NOTE_A4, NOTE_B4, NOTE_C5, NOTE_C5, 0,
	    NOTE_C5, NOTE_D5, NOTE_B4, NOTE_B4, 0,
	    NOTE_A4, NOTE_G4, NOTE_A4, 0,

	    NOTE_E4, NOTE_G4, NOTE_A4, NOTE_A4, 0,
	    NOTE_A4, NOTE_C5, NOTE_D5, NOTE_D5, 0,
	    NOTE_D5, NOTE_E5, NOTE_F5, NOTE_F5, 0,
	    NOTE_E5, NOTE_D5, NOTE_E5, NOTE_A4, 0,

	    NOTE_A4, NOTE_B4, NOTE_C5, NOTE_C5, 0,
	    NOTE_D5, NOTE_E5, NOTE_A4, 0,
	    NOTE_A4, NOTE_C5, NOTE_B4, NOTE_B4, 0,
	    NOTE_C5, NOTE_A4, NOTE_B4, 0,
	    //End of Repeat

	    NOTE_E5, 0, 0, NOTE_F5, 0, 0,
	    NOTE_E5, NOTE_E5, 0, NOTE_G5, 0, NOTE_E5, NOTE_D5, 0, 0,
	    NOTE_D5, 0, 0, NOTE_C5, 0, 0,
	    NOTE_B4, NOTE_C5, 0, NOTE_B4, 0, NOTE_A4,

	    NOTE_E5, 0, 0, NOTE_F5, 0, 0,
	    NOTE_E5, NOTE_E5, 0, NOTE_G5, 0, NOTE_E5, NOTE_D5, 0, 0,
	    NOTE_D5, 0, 0, NOTE_C5, 0, 0,
	    NOTE_B4, NOTE_C5, 0, NOTE_B4, 0, NOTE_A4
	 };
	 int duration[] = {         //duration of each note (in ms) Quarter Note is set to 250 ms
	   125, 125, 250, 125, 125,
	   125, 125, 250, 125, 125,
	   125, 125, 250, 125, 125,
	   125, 125, 375, 125,

	   125, 125, 250, 125, 125,
	   125, 125, 250, 125, 125,
	   125, 125, 250, 125, 125,
	   125, 125, 375, 125,

	   125, 125, 250, 125, 125,
	   125, 125, 250, 125, 125,
	   125, 125, 250, 125, 125,
	   125, 125, 125, 250, 125,

	   125, 125, 250, 125, 125,
	   250, 125, 250, 125,
	   125, 125, 250, 125, 125,
	   125, 125, 375, 375,

	   250, 125,
	   //Rpeat of First Part
	   125, 125, 250, 125, 125,
	   125, 125, 250, 125, 125,
	   125, 125, 375, 125,

	   125, 125, 250, 125, 125,
	   125, 125, 250, 125, 125,
	   125, 125, 250, 125, 125,
	   125, 125, 375, 125,

	   125, 125, 250, 125, 125,
	   125, 125, 250, 125, 125,
	   125, 125, 250, 125, 125,
	   125, 125, 125, 250, 125,

	   125, 125, 250, 125, 125,
	   250, 125, 250, 125,
	   125, 125, 250, 125, 125,
	   125, 125, 375, 375,
	   //End of Repeat

	   250, 125, 375, 250, 125, 375,
	   125, 125, 125, 125, 125, 125, 125, 125, 375,
	   250, 125, 375, 250, 125, 375,
	   125, 125, 125, 125, 125, 500,

	   250, 125, 375, 250, 125, 375,
	   125, 125, 125, 125, 125, 125, 125, 125, 375,
	   250, 125, 375, 250, 125, 375,
	   125, 125, 125, 125, 125, 500
	 };

	 float result;
	 	  for(int i=0;i<203;i++){
	 		  if(stop == 1){
	 			  stop = 0;
	 			  break;
	 		  }
	           int w = duration[i];

	           if(notes[i] == 0){
	               result = 0;

	               }
	           else {
	               result = notes[i];

	               }


	           play_rgb_notes(notes[i]);



	           setfreq(result);

	           delay(w);


	           set_rgb(0, 0, 0);

	 	  }
	 	  TIM2->CCR2 = 0;


}


void play_coffin(){
	 float coffin[] = {

			  NOTE_A4, NOTE_A4, NOTE_A4, NOTE_A4,
			  NOTE_A4, NOTE_A4, NOTE_A4, NOTE_A4,
			  NOTE_A4, NOTE_A4, NOTE_A4, NOTE_A4,
			  NOTE_A4, NOTE_A4, NOTE_A4, NOTE_A4,
			  NOTE_A4, NOTE_A4, NOTE_A4, NOTE_A4,
			  NOTE_D5, NOTE_D5, NOTE_D5, NOTE_D5,
			  NOTE_C5, NOTE_C5, NOTE_C5, NOTE_C5,
			  NOTE_F5, NOTE_F5, NOTE_F5, NOTE_F5,
			  NOTE_G5, NOTE_G5, NOTE_G5, NOTE_G5,
			  NOTE_G5, NOTE_G5, NOTE_G5, NOTE_G5,
			  NOTE_G5, NOTE_G5, NOTE_G5, NOTE_G5,
			  NOTE_C5, NOTE_B4, NOTE_A4, NOTE_F4,
			  NOTE_G4, 0, NOTE_G4, NOTE_D5,
			  NOTE_C5, 0, NOTE_B4, 0,
			  NOTE_A4, 0, NOTE_A4, NOTE_A4,
			  NOTE_C5, 0, NOTE_B4, NOTE_A4,
			  NOTE_G4,0, NOTE_G4, NOTE_A5,
			  NOTE_G5, NOTE_A5, NOTE_G5, NOTE_A5,
			  NOTE_G4,0, NOTE_G4, NOTE_A5,
			  NOTE_G5, NOTE_A5, NOTE_G5, NOTE_A5,
			  NOTE_G4, 0, NOTE_G4, NOTE_D5,
			  NOTE_C5, 0, NOTE_B4, 0,
			  NOTE_A4, 0, NOTE_A4, NOTE_A4,
			  NOTE_C5, 0, NOTE_B4, NOTE_A4,
			  NOTE_G4,0, NOTE_G4, NOTE_A5,
			  NOTE_G5, NOTE_A5, NOTE_G5, NOTE_A5,
			  NOTE_G4,0, NOTE_G4, NOTE_A5,
			  NOTE_G5, NOTE_A5, NOTE_G5, NOTE_A5

	 };

	 int coffinDurations[] = {
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200,
	   200,200,200,200
	   };


	 float result;
	 	  for(int i=0;i< (sizeof(coffinDurations) / sizeof(coffinDurations[0]))  ;i++){
	 		  if(stop == 1){
	 			  stop = 0;
	 			  break;
	 		  }
	           int w = coffinDurations[i];

	           if(coffin[i] == 0){
	               result = 0;

	               }
	           else {
	               result = coffin[i];

	               }


	           play_rgb_notes(coffin[i]);



	           setfreq(result);

	           delay(w);


	           set_rgb(0, 0, 0);

	 	  }
	 	  TIM2->CCR2 = 0;


}


/* USER CODE END 0 */

/**
  * @brief  The application entry point.
  * @retval int
  */
int main(void)
{
  /* USER CODE BEGIN 1 */

  /* USER CODE END 1 */

  /* MCU Configuration--------------------------------------------------------*/

  /* Reset of all peripherals, Initializes the Flash interface and the Systick. */
  HAL_Init();

  /* USER CODE BEGIN Init */

  /* USER CODE END Init */

  /* Configure the system clock */
  SystemClock_Config();

  /* USER CODE BEGIN SysInit */

  /* USER CODE END SysInit */

  /* Initialize all configured peripherals */
  MX_GPIO_Init();
  MX_TIM1_Init();
  MX_TIM2_Init();
  MX_TIM3_Init();
  MX_USART2_UART_Init();
  MX_USART6_UART_Init();
  /* USER CODE BEGIN 2 */
  HAL_TIM_Base_Start(&htim1);
  HAL_TIM_Base_Start(&htim2);
  HAL_TIM_Base_Start(&htim3);
  HAL_TIM_Base_Start_IT(&htim3);




  HAL_TIM_PWM_Start(&htim1, TIM_CHANNEL_1);
  HAL_TIM_PWM_Start(&htim1, TIM_CHANNEL_2);
  HAL_TIM_PWM_Start(&htim1, TIM_CHANNEL_3);

  HAL_TIM_PWM_Start(&htim2, TIM_CHANNEL_2);

  HAL_UART_Receive_IT(&huart2, rec, 6);
  HAL_UART_Receive_IT(&huart6, rec, 6);






	  float result;




  /* USER CODE END 2 */

  /* Infinite loop */
  /* USER CODE BEGIN WHILE */
  while (1)
  {
    /* USER CODE END WHILE */

    /* USER CODE BEGIN 3 */
	  if(!HAL_GPIO_ReadPin(GPIOC,GPIO_PIN_13)){

		  if(bt == 0)
		  {
			  bt = 1;

			  memcpy(rec,0,6);
			  HAL_UART_Receive_IT(&huart6, rec, 6);

			  setfreq(600);
			  set_rgb(0, 255, 0);
			  delay(200);
			  setfreq(800);
			  set_rgb(0, 0,255);
			  delay(200);
			  set_rgb(0, 0, 0);
			  TIM2->CCR2 = 0;

		  }
		  else{
			  bt = 0;
			  memcpy(rec,0,6);
			  HAL_UART_Receive_IT(&huart2, rec, 6);
			  setfreq(800);
			  set_rgb(0, 0, 255);
			  delay(200);
			  setfreq(600);
			  set_rgb(0, 255 , 0);
			  delay(200);
			  set_rgb(0, 0, 0);
			  TIM2->CCR2 = 0;

		  }


	  }


	  if(songs[0] == 1){
		  songs[0] = 0;
		  play_pirates();
	  }
	  else if(songs[3] == 1){
		  songs[3] = 0;
		  play_coffin();
	  }



  }



  /* USER CODE END 3 */
}

/**
  * @brief System Clock Configuration
  * @retval None
  */
void SystemClock_Config(void)
{
  RCC_OscInitTypeDef RCC_OscInitStruct = {0};
  RCC_ClkInitTypeDef RCC_ClkInitStruct = {0};

  /** Configure the main internal regulator output voltage 
  */
  __HAL_RCC_PWR_CLK_ENABLE();
  __HAL_PWR_VOLTAGESCALING_CONFIG(PWR_REGULATOR_VOLTAGE_SCALE1);
  /** Initializes the CPU, AHB and APB busses clocks 
  */
  RCC_OscInitStruct.OscillatorType = RCC_OSCILLATORTYPE_HSI;
  RCC_OscInitStruct.HSIState = RCC_HSI_ON;
  RCC_OscInitStruct.HSICalibrationValue = RCC_HSICALIBRATION_DEFAULT;
  RCC_OscInitStruct.PLL.PLLState = RCC_PLL_ON;
  RCC_OscInitStruct.PLL.PLLSource = RCC_PLLSOURCE_HSI;
  RCC_OscInitStruct.PLL.PLLM = 8;
  RCC_OscInitStruct.PLL.PLLN = 90;
  RCC_OscInitStruct.PLL.PLLP = RCC_PLLP_DIV2;
  RCC_OscInitStruct.PLL.PLLQ = 4;
  if (HAL_RCC_OscConfig(&RCC_OscInitStruct) != HAL_OK)
  {
    Error_Handler();
  }
  /** Initializes the CPU, AHB and APB busses clocks 
  */
  RCC_ClkInitStruct.ClockType = RCC_CLOCKTYPE_HCLK|RCC_CLOCKTYPE_SYSCLK
                              |RCC_CLOCKTYPE_PCLK1|RCC_CLOCKTYPE_PCLK2;
  RCC_ClkInitStruct.SYSCLKSource = RCC_SYSCLKSOURCE_PLLCLK;
  RCC_ClkInitStruct.AHBCLKDivider = RCC_SYSCLK_DIV1;
  RCC_ClkInitStruct.APB1CLKDivider = RCC_HCLK_DIV2;
  RCC_ClkInitStruct.APB2CLKDivider = RCC_HCLK_DIV1;

  if (HAL_RCC_ClockConfig(&RCC_ClkInitStruct, FLASH_LATENCY_2) != HAL_OK)
  {
    Error_Handler();
  }
}

/**
  * @brief TIM1 Initialization Function
  * @param None
  * @retval None
  */
static void MX_TIM1_Init(void)
{

  /* USER CODE BEGIN TIM1_Init 0 */

  /* USER CODE END TIM1_Init 0 */

  TIM_MasterConfigTypeDef sMasterConfig = {0};
  TIM_OC_InitTypeDef sConfigOC = {0};
  TIM_BreakDeadTimeConfigTypeDef sBreakDeadTimeConfig = {0};

  /* USER CODE BEGIN TIM1_Init 1 */

  /* USER CODE END TIM1_Init 1 */
  htim1.Instance = TIM1;
  htim1.Init.Prescaler = 1384-1;
  htim1.Init.CounterMode = TIM_COUNTERMODE_UP;
  htim1.Init.Period = 255-1;
  htim1.Init.ClockDivision = TIM_CLOCKDIVISION_DIV1;
  htim1.Init.RepetitionCounter = 0;
  htim1.Init.AutoReloadPreload = TIM_AUTORELOAD_PRELOAD_DISABLE;
  if (HAL_TIM_PWM_Init(&htim1) != HAL_OK)
  {
    Error_Handler();
  }
  sMasterConfig.MasterOutputTrigger = TIM_TRGO_RESET;
  sMasterConfig.MasterSlaveMode = TIM_MASTERSLAVEMODE_DISABLE;
  if (HAL_TIMEx_MasterConfigSynchronization(&htim1, &sMasterConfig) != HAL_OK)
  {
    Error_Handler();
  }
  sConfigOC.OCMode = TIM_OCMODE_PWM1;
  sConfigOC.Pulse = 0;
  sConfigOC.OCPolarity = TIM_OCPOLARITY_HIGH;
  sConfigOC.OCNPolarity = TIM_OCNPOLARITY_HIGH;
  sConfigOC.OCFastMode = TIM_OCFAST_DISABLE;
  sConfigOC.OCIdleState = TIM_OCIDLESTATE_RESET;
  sConfigOC.OCNIdleState = TIM_OCNIDLESTATE_RESET;
  if (HAL_TIM_PWM_ConfigChannel(&htim1, &sConfigOC, TIM_CHANNEL_1) != HAL_OK)
  {
    Error_Handler();
  }
  if (HAL_TIM_PWM_ConfigChannel(&htim1, &sConfigOC, TIM_CHANNEL_2) != HAL_OK)
  {
    Error_Handler();
  }
  if (HAL_TIM_PWM_ConfigChannel(&htim1, &sConfigOC, TIM_CHANNEL_3) != HAL_OK)
  {
    Error_Handler();
  }
  sBreakDeadTimeConfig.OffStateRunMode = TIM_OSSR_DISABLE;
  sBreakDeadTimeConfig.OffStateIDLEMode = TIM_OSSI_DISABLE;
  sBreakDeadTimeConfig.LockLevel = TIM_LOCKLEVEL_OFF;
  sBreakDeadTimeConfig.DeadTime = 0;
  sBreakDeadTimeConfig.BreakState = TIM_BREAK_DISABLE;
  sBreakDeadTimeConfig.BreakPolarity = TIM_BREAKPOLARITY_HIGH;
  sBreakDeadTimeConfig.AutomaticOutput = TIM_AUTOMATICOUTPUT_DISABLE;
  if (HAL_TIMEx_ConfigBreakDeadTime(&htim1, &sBreakDeadTimeConfig) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN TIM1_Init 2 */

  /* USER CODE END TIM1_Init 2 */
  HAL_TIM_MspPostInit(&htim1);

}

/**
  * @brief TIM2 Initialization Function
  * @param None
  * @retval None
  */
static void MX_TIM2_Init(void)
{

  /* USER CODE BEGIN TIM2_Init 0 */

  /* USER CODE END TIM2_Init 0 */

  TIM_ClockConfigTypeDef sClockSourceConfig = {0};
  TIM_MasterConfigTypeDef sMasterConfig = {0};
  TIM_OC_InitTypeDef sConfigOC = {0};

  /* USER CODE BEGIN TIM2_Init 1 */
  htim2.Instance = TIM2;
  htim2.Init.Prescaler = 1500-1;
  /* USER CODE END TIM2_Init 1 */
  htim2.Instance = TIM2;
  htim2.Init.Prescaler = 1500-1;
  htim2.Init.CounterMode = TIM_COUNTERMODE_UP;
  htim2.Init.Period = 1000-1;
  htim2.Init.ClockDivision = TIM_CLOCKDIVISION_DIV1;
  htim2.Init.AutoReloadPreload = TIM_AUTORELOAD_PRELOAD_DISABLE;
  if (HAL_TIM_Base_Init(&htim2) != HAL_OK)
  {
    Error_Handler();
  }
  sClockSourceConfig.ClockSource = TIM_CLOCKSOURCE_INTERNAL;
  if (HAL_TIM_ConfigClockSource(&htim2, &sClockSourceConfig) != HAL_OK)
  {
    Error_Handler();
  }
  if (HAL_TIM_PWM_Init(&htim2) != HAL_OK)
  {
    Error_Handler();
  }
  sMasterConfig.MasterOutputTrigger = TIM_TRGO_RESET;
  sMasterConfig.MasterSlaveMode = TIM_MASTERSLAVEMODE_DISABLE;
  if (HAL_TIMEx_MasterConfigSynchronization(&htim2, &sMasterConfig) != HAL_OK)
  {
    Error_Handler();
  }
  sConfigOC.OCMode = TIM_OCMODE_PWM1;
  sConfigOC.Pulse = 0;
  sConfigOC.OCPolarity = TIM_OCPOLARITY_HIGH;
  sConfigOC.OCFastMode = TIM_OCFAST_DISABLE;
  if (HAL_TIM_PWM_ConfigChannel(&htim2, &sConfigOC, TIM_CHANNEL_2) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN TIM2_Init 2 */
  sConfigOC.Pulse = (htim2.Init.Period + 1)/2;
  /* USER CODE END TIM2_Init 2 */
  HAL_TIM_MspPostInit(&htim2);

}

/**
  * @brief TIM3 Initialization Function
  * @param None
  * @retval None
  */
static void MX_TIM3_Init(void)
{

  /* USER CODE BEGIN TIM3_Init 0 */

  /* USER CODE END TIM3_Init 0 */

  TIM_ClockConfigTypeDef sClockSourceConfig = {0};
  TIM_MasterConfigTypeDef sMasterConfig = {0};

  /* USER CODE BEGIN TIM3_Init 1 */

  /* USER CODE END TIM3_Init 1 */
  htim3.Instance = TIM3;
  htim3.Init.Prescaler = 10000;
  htim3.Init.CounterMode = TIM_COUNTERMODE_UP;
  htim3.Init.Period = 9000;
  htim3.Init.ClockDivision = TIM_CLOCKDIVISION_DIV1;
  htim3.Init.AutoReloadPreload = TIM_AUTORELOAD_PRELOAD_DISABLE;
  if (HAL_TIM_Base_Init(&htim3) != HAL_OK)
  {
    Error_Handler();
  }
  sClockSourceConfig.ClockSource = TIM_CLOCKSOURCE_INTERNAL;
  if (HAL_TIM_ConfigClockSource(&htim3, &sClockSourceConfig) != HAL_OK)
  {
    Error_Handler();
  }
  sMasterConfig.MasterOutputTrigger = TIM_TRGO_RESET;
  sMasterConfig.MasterSlaveMode = TIM_MASTERSLAVEMODE_DISABLE;
  if (HAL_TIMEx_MasterConfigSynchronization(&htim3, &sMasterConfig) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN TIM3_Init 2 */

  /* USER CODE END TIM3_Init 2 */

}

/**
  * @brief USART2 Initialization Function
  * @param None
  * @retval None
  */
static void MX_USART2_UART_Init(void)
{

  /* USER CODE BEGIN USART2_Init 0 */

  /* USER CODE END USART2_Init 0 */

  /* USER CODE BEGIN USART2_Init 1 */

  /* USER CODE END USART2_Init 1 */
  huart2.Instance = USART2;
  huart2.Init.BaudRate = 9600;
  huart2.Init.WordLength = UART_WORDLENGTH_8B;
  huart2.Init.StopBits = UART_STOPBITS_1;
  huart2.Init.Parity = UART_PARITY_NONE;
  huart2.Init.Mode = UART_MODE_TX_RX;
  huart2.Init.HwFlowCtl = UART_HWCONTROL_NONE;
  huart2.Init.OverSampling = UART_OVERSAMPLING_16;
  if (HAL_UART_Init(&huart2) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN USART2_Init 2 */

  /* USER CODE END USART2_Init 2 */

}

/**
  * @brief USART6 Initialization Function
  * @param None
  * @retval None
  */
static void MX_USART6_UART_Init(void)
{

  /* USER CODE BEGIN USART6_Init 0 */

  /* USER CODE END USART6_Init 0 */

  /* USER CODE BEGIN USART6_Init 1 */

  /* USER CODE END USART6_Init 1 */
  huart6.Instance = USART6;
  huart6.Init.BaudRate = 9600;
  huart6.Init.WordLength = UART_WORDLENGTH_8B;
  huart6.Init.StopBits = UART_STOPBITS_1;
  huart6.Init.Parity = UART_PARITY_NONE;
  huart6.Init.Mode = UART_MODE_TX_RX;
  huart6.Init.HwFlowCtl = UART_HWCONTROL_NONE;
  huart6.Init.OverSampling = UART_OVERSAMPLING_16;
  if (HAL_UART_Init(&huart6) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN USART6_Init 2 */

  /* USER CODE END USART6_Init 2 */

}

/**
  * @brief GPIO Initialization Function
  * @param None
  * @retval None
  */
static void MX_GPIO_Init(void)
{
  GPIO_InitTypeDef GPIO_InitStruct = {0};

  /* GPIO Ports Clock Enable */
  __HAL_RCC_GPIOC_CLK_ENABLE();
  __HAL_RCC_GPIOA_CLK_ENABLE();

  /*Configure GPIO pin Output Level */
  HAL_GPIO_WritePin(GPIOA, GPIO_PIN_5, GPIO_PIN_RESET);

  /*Configure GPIO pin : PC13 */
  GPIO_InitStruct.Pin = GPIO_PIN_13;
  GPIO_InitStruct.Mode = GPIO_MODE_INPUT;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  HAL_GPIO_Init(GPIOC, &GPIO_InitStruct);

  /*Configure GPIO pin : PA5 */
  GPIO_InitStruct.Pin = GPIO_PIN_5;
  GPIO_InitStruct.Mode = GPIO_MODE_OUTPUT_PP;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  GPIO_InitStruct.Speed = GPIO_SPEED_FREQ_LOW;
  HAL_GPIO_Init(GPIOA, &GPIO_InitStruct);

}

/* USER CODE BEGIN 4 */

void HAL_TIM_PeriodElapsedCallback( TIM_HandleTypeDef *htim ){

	r_d ++;

}

void HAL_UART_RxCpltCallback(UART_HandleTypeDef *huart)
{

		if(bt == 1)
			HAL_UART_Transmit(&huart6, rec, 6, 20);
		else
			HAL_UART_Transmit(&huart2, rec, 6, 20);




	stop = 1;
	if(( (huart == &huart2) && (bt == 0) ) || ((huart == &huart6) && (bt == 1))  ){
	sscanf(rec, "%d", &i);

	rxcmp = 1;
	memcpy(rec,0,6);

	  if( (i == NOTE_C4) || (i == NOTE_D4) || (i == NOTE_E4) || (i == NOTE_F4) || (i == NOTE_G4) || (i == NOTE_A4) || (i == NOTE_B4) || (i == NOTE_C5) || (i == NOTE_D5) || (i == NOTE_E5) || (i == NOTE_F5) || (i == NOTE_G5) || (i == NOTE_A5) || (i == NOTE_B5) ){

		setfreq(i);

		if(r_start){
			if((previous != 0) && last_index == 0 ){
				record_list[record_length] = 0;
				record_length ++;
				int drc = (r_d * 9000) + TIM3->CNT;
				record_duraction[record_length - 1] = drc;
			}

			if(last_index > 0){
				int drc = (r_d * 9000) + TIM3->CNT;
			    record_duraction[record_length - 1] = drc;
			}


			TIM3->CNT = 0;
			r_d = 0;
			record_list[record_length] = i;
			record_length ++;
			previous = i;

		}


		if(last_index <= 9){
			nota_stack[last_index] = i;
			last_index++;
		}else{
			last_index = 0;
			nota_stack[last_index] = i;
			last_index++;
		}




		play_rgb_notes(i);
		i=0;

	  }
	else if ( (abs(i) == NOTE_C4) || (abs(i) == NOTE_D4) || (abs(i) == NOTE_E4) || (abs(i) == NOTE_F4) || (abs(i) == NOTE_G4) || (abs(i) == NOTE_A4) || (abs(i) == NOTE_B4) || (abs(i) == NOTE_C5) || (abs(i) == NOTE_D5) || (abs(i) == NOTE_E5) || (abs(i) == NOTE_F5) || (abs(i) == NOTE_G5) || (abs(i) == NOTE_A5) || (abs(i) == NOTE_B5)  ){

		  	   if ( (int) abs(i) == nota_stack[last_index-1] ){
		  		set_rgb(0, 0, 0);
		  		TIM2->CCR2 = 0;
		  		for(int k=0;k<10;k++)
		  			nota_stack[k] = 0;

		  		last_index = 0;

		  		if(r_start){
		  		int drc = (r_d * 9000) + TIM3->CNT;
		  		record_duraction[record_length - 1] = drc;
		  		TIM3->CNT = 0;



		  		}

		  	  }
		  	  else{



		  		  int n_i = get_index(abs(i));

		  		  for(int k = n_i ; 10>k;k++){
		  			  nota_stack[k] = nota_stack[k+1];
		  		  }
		  		  last_index --;


		  	  }



		  	  i = 0;

}
	else if((i == 271) || (i == -271) ){


	 			  if(i == 271){

	 				  for(int j = 0 ; 200 > j ; j++){
	 					  record_list[j] = 0;
	 					  record_duraction[j] = 0;
	 				  }
	 				  record_length = 0;
	 				  r_start = 1;

	 				 set_rgb(255, 0, 0);
	 				 setfreq(200);
	 				 	 delay(200);
	 				 set_rgb(0, 255, 0);
	 				 setfreq(400);
	 				 	 delay(200);
	 				 set_rgb(0, 0, 255);
	 				 setfreq(600);
	 				 	 delay(200);
	 				 set_rgb(0, 0, 0);
	 				 TIM2->CCR2 = 0;
	 				 previous = 0;
	 			  }
	 			  else{
	 				  	  r_start = 0;
	 					 set_rgb(0, 0, 255);
	 					 setfreq(600);
	 					 	 delay(200);
	 					 set_rgb(0, 255, 0);
	 					 setfreq(400);
	 					 	 delay(200);
	 					 set_rgb(255, 0, 0);
	 					 setfreq(200);
	 					 	 delay(200);
	 					 set_rgb(0, 0, 0);
	 					 TIM2->CCR2 = 0;


	 			  }

	 			  i = 0;
	 		  }
	 		  else if( (i == 272) || (i == 273) || (i == 274) || (i == 275)   ){
	 			  float song_speed = 0;
	 			  if(i == 272)
	 				  song_speed = 0.5;
	 			  else if (i == 273)
	 				  song_speed = 1;
	 			  else if (i == 274)
	 				  song_speed = 1.5;
	 			  else if (i == 275)
	 				  song_speed = 2;


	 			  int result;
	 			  for(int i=0;i<record_length;i++){
	 		          int w = record_duraction[i] / 10;

	 		          w = w / song_speed;

	 		          if(record_list[i] == 0){
	 		              result = 0;

	 		              }
	 		          else {
	 		              result = record_list[i];

	 		              }

	 					play_rgb_notes((int)record_list [i]);



	 		          setfreq(result);
	 		          delay(w);
	 		          set_rgb(0, 0, 0);

	 			  }
	 			  TIM2->CCR2 = 0;
	 			  i = 0;
	 		  }
	 		  else if(i >= 100 && i < 105){
	 			  stop = 0;
	 			  if(i == 100)
	 				  songs[0] = 1;
	 			  else if(i == 104)
	 				  songs[3] = 1;

	 		  }


	}
	  if(bt == 0)
		  HAL_UART_Receive_IT(&huart2, rec, 6);
	  else
		  HAL_UART_Receive_IT(&huart6, rec, 6);

}
/* USER CODE END 4 */

/**
  * @brief  This function is executed in case of error occurrence.
  * @retval None
  */
void Error_Handler(void)
{
  /* USER CODE BEGIN Error_Handler_Debug */
  /* User can add his own implementation to report the HAL error return state */

  /* USER CODE END Error_Handler_Debug */
}

#ifdef  USE_FULL_ASSERT
/**
  * @brief  Reports the name of the source file and the source line number
  *         where the assert_param error has occurred.
  * @param  file: pointer to the source file name
  * @param  line: assert_param error line source number
  * @retval None
  */
void assert_failed(uint8_t *file, uint32_t line)
{ 
  /* USER CODE BEGIN 6 */
  /* User can add his own implementation to report the file name and line number,
     tex: printf("Wrong parameters value: file %s on line %d\r\n", file, line) */
  /* USER CODE END 6 */
}
#endif /* USE_FULL_ASSERT */

/************************ (C) COPYRIGHT STMicroelectronics *****END OF FILE****/
