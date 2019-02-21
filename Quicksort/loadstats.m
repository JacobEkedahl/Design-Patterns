%% Code to analyze merging data
filename = 'quicksort_4.txt';
delimiterIn = ' ';
headerlines = 1;
A = importdata(filename,delimiterIn,headerlines);

%Load the mearsured periods from the array
Times = A.data(:, 3);

% Calculate the mean
m = mean(Times);

disp(Times)
%just take some threshold value from a matrix
ThresholdArray = A.data(2,2); 
fprintf("here is the average %f and threshold: ", m);

fprintf("here is the threshold value %d", ThresholdArray);

% fit a normal distribution to the data
pd = fitdist(Times,'Normal');
% compute the 95% confidence interval for the distribution
% parameters
ci = paramci(pd,'Alpha',.05);

x = Times;
y = 20:0.1:30

plot(x)
hold on
plot(x, ci)                                % Plot 95% Confidence Intervals Of All Experiments
hold off
grid



%% stuff the measured times in an array.

for k = [1,5]
   disp(A.colheaders{1, k})
  disp(A.data(:, k))
   disp(' ')
end