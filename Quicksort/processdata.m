filename = 'mergesort_1.txt';
delimiterIn = ' ';
headerlines = 1;
FULLDATA = importdata(filename,delimiterIn,headerlines);

PROCESSED = FULLDATA.data(:,1:end-1);

NUMiterations = length(PROCESSED)/4;

NUMcores = FULLDATA.data(:,end);

[row,col] = size(PROCESSED);

mArraySort = [];
mParallelSort = [];
mMergeSort = [];
mQuickSort = [];
 
for k = 1:row
    for n =1:col
        if n==1
             mArraySort = [mArraySort,PROCESSED(k,n)/1000000];
        end
        if n==2
             mParallelSort = [mParallelSort,PROCESSED(k,n)/1000000];
        end
        if n==3
             mMergeSort = [mMergeSort,PROCESSED(k,n)/1000000];
        end
        if n==4
             mQuickSort = [mQuickSort,PROCESSED(k,n)/1000000];
        end
    end
    if rem(k,NUMiterations)==0
        meanAs = mean(mArraySort);
        stdDevsAs = std(mArraySort);
        SEMAS = stdDevsAs / sqrt(length(mArraySort));
        ConAS = SEMAS  * tinv(0.975, length(mArraySort)-1);
        hold on
        figure(1)
        xfact = NUMcores(k);
        
        SAS = errorbar(xfact,meanAs,ConAS,'o');
        SAS.Color = [1 0.4 0.6];
        title('Array Sort')
        grid on
        xlim([0 8]);
        ylim([0 300]);
        ylabel('time m/S'); 
        xlabel('number of cores');
        
        
        %% Parallel sort graphing
        meanPs = mean(mParallelSort);
        stdDevPs = std(mParallelSort);
        SEMPS = stdDevPs / sqrt(length(mParallelSort));
        ConPS = SEMPS * tinv(0.975, length(mParallelSort)-1);
 
        hold on
        figure(2)
        xfact = NUMcores(k);
        %disp("display " + xfact);
        SPS = errorbar(xfact,meanPs,ConPS,'o');
        SPS.Color = [1 0.4 0.6];
        title("Parallel Sort");
        grid on
        xlim([0 8]);
        ylim([0 150]);
        ylabel('time m/S'); 
        xlabel('number of cores');

        %% Merge sort graphing
        
        meanMs = mean(mMergeSort);
        stdDevMs = std(mMergeSort);
        SEMMS = stdDevMs / sqrt(length(mMergeSort));
        ConMS = SEMMS * tinv(0.975, length(mMergeSort)-1);
        
        hold on
        figure(3)
        xfact = NUMcores(k);
        SMS = errorbar(xfact,meanMs,ConMS,'o');
        SMS.Color = [1 0.4 0.6];
        title("Merge Sort");
        grid on
        xlim([0 8]);
        ylim([0 300]);
        ylabel('time m/S'); 
        xlabel('number of cores');
        
        
         %% Quick sort graphing
        
        meanQs = mean(mQuickSort);
        stdDevQs = std(mQuickSort);
        SEMQS = stdDevQs / sqrt(length(mQuickSort));
        ConQS = SEMQS * tinv(0.975, length(mQuickSort)-1);
        
        hold on
        figure(4)
        xfact = NUMcores(k);
      
        SQS = errorbar(xfact,meanQs,ConQS,'o');
        SQS.Color = [1 0.4 0.6];
        title("Quick Sort");
        grid on
        xlim([0 8]);
        ylim([0 300]);


        
       
        mParallelSort = [];
        mArraySort    = [];
        mMergeSort    = [];
        mQuickSort    = [];

        

        
    end
end

