# PdfApp
![1](https://user-images.githubusercontent.com/60996869/189036555-d4d40f90-0d7e-4bc5-bab3-5f79579954a1.jpeg)
![2](https://user-images.githubusercontent.com/60996869/189036667-69e501f7-7cce-4275-b3c6-bc37057267ce.jpeg)
![3](https://user-images.githubusercontent.com/60996869/189036677-f5cdf245-7388-454c-b816-8ab42e13ed2b.jpeg)
 
I generated a Dynamic multi lingual PDF using canvas drawing tools.(using bitmap)
where in I generated a PDF from the Recycler View .

XML

1 -> pdf_layout_page.xml : contains the layout of the PDF to be Bitmapped and then drawn on canvas using inbubild PDFDocument function of Android

2-> recycler_lang.xml : contains the recycler view which takes , language name and language text as TextView , with  a View to Draw lines 

3 -> activity_main.xml : contains two button which change visiblity on click, and a PDF viewer implemented using 
                          (implementation 'com.github.mhiew:android-pdf-viewer:3.2.0-beta.3') and <com.github.barteksc.pdfviewer.PDFView
                          //xml layout for pdf view



KOTLIN

1 -> MainActiviy.kt : creates view of activity_main.xml and initialises all UI components and is driver class for all other class.
                      it also is used to get data into two data classes 
                      i) LangDetails
                      ii) PdfDetails
                      
                      it also is used to open the generated pdf using the pdfview library

2 -> RecyclerAdapter.kt: it is used to insert values into recycler view from data classes

3 -> PDFConverter.kt : it created bitmap of the recycler view, returns the bitmap to be used in drawing the bitmap to canvas. 
                        creates PDF in internal Storage of the application.
