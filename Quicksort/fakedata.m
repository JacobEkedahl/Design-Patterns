
disp(A);

%x =linspace(0,10,4);
x = 1:4;                                   % Create Data
y1C_qsrt = rand(1,4);                            % Create Data
y2C_qsrt = rand(1,4);                            % Create Data
y3C_qsrt = rand(1,4);% Create Data
y4C_qsrt = rand(1,4);
Sze = 3;
ym2 = [ mean(y1C_qsrt); mean(y2C_qsrt); mean(y3C_qsrt); mean(y4C_qsrt)];
ymSDEV = [std(y1C_qsrt); std(y2C_qsrt); std(y3C_qsrt);std(y4C_qsrt)];
%SEM2 = std(ym2) / sqrt(4);   
Conf = ymSDEV * tinv(0.975, Sze-1);


figure
E = errorbar(x, ym2, Conf,'o');
yl = ym2; % Get current limits.
xlim([0 8])
ylim([0 10])
grid

