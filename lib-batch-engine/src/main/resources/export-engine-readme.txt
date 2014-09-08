Selamat datang rekan developer, 

Export engine.
Balicamp, April 2013
------------------------------------------------------------------
library ini membantu anda dalam memproses generate file text(delimited dengan , | etc) dari table dengan select statement yang di definisikan
yang anda perlukan : 
1. spring bean yang implement : id.co.sigma.commonlib.exportengine.data.IExportToTextFileMetadataProvider 		--> ini menentukan select statement, delimieter target etc
2. spring bean yang implement : id.co.sigma.commonlib.exportengine.io.ITargetFileLocationGenerator  			--> ini menyediakan kemana file akan di tulis

