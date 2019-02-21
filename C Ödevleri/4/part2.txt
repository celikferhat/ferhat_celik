void zeroPadding(double outputArr[], int outputArrSize, int paddingWidth){
    int i = 0;

    for(i = 0; i<paddingWidth; ++i){
        outputArr[i]=0; 
    }

    for(i = outputArrSize-paddingWidth; i<outputArrSize; ++i){
        outputArr[i]=0;     
    }
}

void halfPadding(double outputArr[], int outputArrSize, int paddingWidth){
    int i = 0;

    for(i = 0; i<paddingWidth; ++i){
        outputArr[i]=outputArr[paddingWidth]/2; 
    }

    for(i = outputArrSize-paddingWidth; i<outputArrSize; ++i){
        outputArr[i]=outputArr[outputArrSize-paddingWidth-1]/2;
    }
}

void samePadding(double outputArr[], int outputArrSize, int paddingWidth){
    int i = 0;

    for(i = 0; i<paddingWidth; ++i){
        outputArr[i]=outputArr[paddingWidth];   
    }

    for(i = outputArrSize-paddingWidth; i<outputArrSize; ++i){
        outputArr[i]=outputArr[outputArrSize-paddingWidth-1];
    }
}




void addPadding(double inputArr[], int inputArraySize, double outputArr[], int *outputArraySize, int paddingWidth, void paddingMethod(double[], int, int)){
    int i = 0;

    *outputArraySize = inputArraySize + 2*paddingWidth;
    for(i = 0; i<inputArraySize; ++i){
        outputArr[i+paddingWidth]=inputArr[i];
    }

    paddingMethod(outputArr, *outputArraySize, paddingWidth);
}


int convolution(double inputArr[], int inputArrSize, double kernel[], int kernelSize, 
                double outputArr[], int *outputArrSize, int stride, PaddingType type){
    int i = 0, j = 0;
    double result = 0;
    double paddedArr[256];
    int paddedArrSize = 0;
    int paddingSize;
    paddingSize = (kernelSize-1)/2;

    if(kernelSize>inputArrSize)
        return -1;

    switch(type){
        case ZERO:
            addPadding(inputArr, inputArrSize, paddedArr, &paddedArrSize, paddingSize, zeroPadding);
            break;
        case HALF:
            addPadding(inputArr, inputArrSize, paddedArr, &paddedArrSize, paddingSize, halfPadding);
            break;
        case SAME:
            addPadding(inputArr, inputArrSize, paddedArr, &paddedArrSize, paddingSize, samePadding);
            break;
        default:
            addPadding(inputArr, inputArrSize, paddedArr, &paddedArrSize, paddingSize, zeroPadding);
    }

    for(i = 0; i<paddedArrSize-kernelSize+1; i+=stride){
        result = 0;
        for(j = 0; j<kernelSize; ++j){
            result+=paddedArr[i+j]*kernel[j];
        }

        outputArr[i/stride]=result;
    }
    
    *outputArrSize = ((inputArrSize-kernelSize+2*paddingSize)/stride)+1;
    return 0;
}
