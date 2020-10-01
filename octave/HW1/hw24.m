clear all
clc


list=textread("hw1cd.txt")#檔案分 ab cd 兩題
test1=size(list)

shape=size(list)(1)/2
list=reshape(list,[2,shape])#原始數據
mat1=[]
for i=1:shape
  if (i==1)
    mat1(i)=1
  else
    mat1(i)=0
  endif
endfor
mat=[]
for i=1:shape-2
  for j=1:shape
    if (j==i)
      mat(j)=(list(1,i+1)-list(1,i))/6
    elseif(j==i+1)
      mat(j)=(list(1,i+2)-list(1,i))/3
    elseif(j==i+2)
      mat(j)=(list(1,i+2)-list(1,i+1))/6 
    else
      mat(j)=0
    endif
  
  endfor
mat1=[mat1;mat]
endfor
mat2=[]
for i=1:shape
  if (i==shape)
    mat2(i)=1
  else
    mat2(i)=0
  endif
endfor
mat1=[mat1;mat2]#原矩陣
mm=inv(mat1)#算出來的反矩陣
div=[]
div(1)=0
for i=2:shape-1
  div(i)=(list(2,i+1)-list(2,i))/(list(1,i+1)-list(1,i))-(list(2,i)-list(2,i-1))/(list(1,i)-list(1,i-1))
endfor
div(shape)=0
div=div'
step=mat1\div#p``(x1)到p``(x11)
ans=[]#答案
ansx=[]
for i=1:shape-1
  temp=[list(1,i):0.005:list(1,i+1)]
  x=temp
  temp=(step(i)/6)*(((list(1,i+1)-temp).^3)/(list(1,i+1)-list(1,i))-(list(1,i+1)-list(1,i))*(list(1,i+1)-temp))+(step(i+1)/6)*(((temp-list(1,i)).^3/(list(1,i+1)-list(1,i))-(list(1,i+1)-list(1,i))*(temp-list(1,i))) ) +list(2,i)*(list(1,i+1)-temp)/(list(1,i+1)-list(1,i))+list(2,i+1)*(temp-list(1,i))/(list(1,i+1)-list(1,i))     
  
  ans=[ans,temp]  
  ansx=[ansx,x]
endfor


plot(ansx,ans,'r')
print("-djpeg",["pictured_final",'.jpg'])
step