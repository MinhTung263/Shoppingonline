<?php 
require_once "connect.php";
$sql="SELECT * FROM danhmuc ORDER BY RAND() ";
	
class DanhMuc{
	function DanhMuc($idDM,$TenDM,$HinhDM){
		$this->idDM=$idDM;
		$this->TenDM=$TenDM;
		$this->HinhDM=$HinhDM;
	}
}
	$MangDM= array();
		if (isset($_GET["idDM"])) {
		$idLoaiSP=$_GET["idDM"];
		$sql="SELECT * FROM danhmuc WHERE FIND_IN_SET('$idDM',idDM)";
		$dataDM=mysqli_query($connect,$sql);	
		$rowDM=mysqli_fetch_assoc($dataDM);
	}
	$data=mysqli_query($connect,$sql);	
	while ($row=mysqli_fetch_assoc($data)) {
		array_push($MangDM, new DanhMuc($row["idDM"],$row["TenDM"],$row["HinhDM"]));
	}
	echo json_encode($MangDM);
 ?>