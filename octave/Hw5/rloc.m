pkg load control
 s = tf('s');
 #g = (1+(20*(s+3)/(s*(s+1)*(s+4))));
 #g= (s+2)/(s*s)/(1+(s+2)/(s*s));
 #g=50*(s*s-4*s+13)/((s+2)*(s+4)) * 1/s
 g=100*(s+1)/(s*(s+2)*(s+5));
 #g=100*(s+2)/(s*(s+1)*(s+4));
 bode(g);
 #nyquist(g);