disp(RAW);


RDATA = RAW.data(:,1:end-1);
HEADERS = (RAW.colheaders);

%%ARRAYSORT
A1C = RDATA(1:5);
A2C = RDATA(6:10);
A3C = RDATA(10:14);
A4C = RDATA(15:20);

%%ParrallelSort
P1C = RDATA(1:5,2);
P2C = RDATA(6:10,2);
P3C = RDATA(10:14,2);
P4C = RDATA(15:20,2);

%%MERGESORT
M1C = RDATA(1:5,3);
M2C = RDATA(6:10,3);
M3C = RDATA(10:14,3);
M4C = RDATA(15:20,3);
%%QUICKSORT
Q1C = RDATA(1:5,end);
Q2C = RDATA(6:10,end);
Q3C = RDATA(10:14,end);
Q4C = RDATA(15:20,end);

%% ARRAY SORT MEAN
MA1C = mean(A1C);
MA2C = mean(A2C);
MA3C = mean(A3C);
MA4C = mean(A4C);
AMPLOT = [MA1C;MA2C;MA3C;MA4C];
x = 1:4;
figure
plot(x, AMPLOT,'o');
yl = AMPLOT; % Get current limits.
xlim([0 8])
ylim([150 600])
grid
%% PARALLEL SORT MEAN
MP1C = mean(P1C);
MP2C = mean(P2C);
MP3C = mean(P3C);
MP4C = mean(P4C);


%% MERGE SORT MEAN

MM1C = mean(M1C);
MM2C = mean(M1C);
MM3C = mean(M1C);
MM4C = mean(M1C);

%% QUICK SORT MEAN
MQ1C = mean(Q1C);
MQ2C = mean(Q2C);
MQ3C = mean(Q3C);
MQ4C = mean(Q4C);
