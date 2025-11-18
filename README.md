# CS Student Manager

컴퓨터공학 학생들을 위한 종합 학습 도구 Android 앱입니다.

## 주요 기능

### 1. 바이트코딩 학습 도구
- **진법 변환기**
  - 10진수를 2진수, 8진수, 16진수로 변환
  - 실시간 변환 결과 확인

- **비트 연산 실습**
  - 기본 비트 연산: AND, OR, XOR, NOT
  - 시프트 연산: 왼쪽 시프트(<<), 오른쪽 시프트(>>)
  - 고급 연산: 부호 없는 오른쪽 시프트(>>>)
  - 비트 카운팅: 1과 0의 개수 세기
  - 2진수 표현과 함께 결과 표시

### 2. GitHub 관리
- **사용자 검색 및 정보 조회**
  - GitHub 사용자명으로 검색
  - 프로필 정보 표시 (저장소 수, 팔로워, 팔로잉)

- **저장소 관리**
  - 공개 저장소 목록 조회
  - 저장소별 Stars, Forks, 사용 언어 정보
  - GitHub API를 통한 실시간 데이터 가져오기

### 3. 컴공 용어사전
- **용어 데이터베이스**
  - Room Database를 활용한 로컬 데이터 저장
  - 14개의 사전 등록된 CS 용어
  - 용어 추가/삭제 기능

- **스마트 검색**
  - 카테고리별 분류 (알고리즘, 자료구조, 네트워크, 운영체제, 데이터베이스, 프로그래밍, 웹, 기타)
  - 실시간 검색 필터링
  - 용어와 정의를 동시에 검색

### 4. 앱 설정
- **다크 모드 지원**
  - Material Design Day/Night 테마
  - DataStore를 활용한 설정 영속성
  - 메뉴에서 쉽게 전환 가능

## 기술 스택

### 언어 및 프레임워크
- **Kotlin**: 최신 Android 개발 언어
- **Android SDK 34**: 최신 Android 기능 활용

### 아키텍처 및 디자인 패턴
- **MVVM 패턴**: ViewModel과 LiveData를 활용한 반응형 UI
- **Repository 패턴**: 데이터 소스 추상화

### 데이터 관리
- **Room Database**: 로컬 데이터 영속성 (용어사전)
  - Entity, DAO, Database 구조
  - Flow를 활용한 반응형 데이터 스트림
  - 초기 데이터 자동 삽입

- **DataStore**: 사용자 설정 저장 (다크 모드 선호도)
  - Preferences DataStore 사용
  - 코루틴 Flow로 반응형 설정 관리

### UI/UX
- **Material Design 3**: 최신 디자인 가이드라인
- **ViewBinding**: 안전한 뷰 참조
- **ViewPager2**: 탭 네비게이션
- **RecyclerView**: 효율적인 리스트 표시
- **CardView**: 아름다운 카드 레이아웃

### 네트워킹
- **Retrofit2**: GitHub API 통신
- **Gson Converter**: JSON 직렬화/역직렬화
- **OkHttp3**: HTTP 클라이언트
  - Logging Interceptor로 디버깅 지원

### 비동기 처리
- **Kotlin Coroutines**: 비동기 작업 처리
  - lifecycleScope로 생명주기 인식 코루틴
  - Flow를 활용한 데이터 스트리밍

## 프로젝트 구조

```
app/src/main/java/com/csstudent/manager/
├── MainActivity.kt                    # 메인 액티비티 (다크 모드 설정)
├── api/
│   └── GithubApiService.kt           # GitHub API 서비스
├── data/
│   ├── Term.kt                       # 용어 Entity
│   ├── TermRepository.kt             # 용어 Repository
│   ├── GithubUser.kt                 # GitHub 사용자 모델
│   ├── GithubRepository.kt           # GitHub 저장소 모델
│   ├── FavoriteRepository.kt         # 즐겨찾기 Entity
│   └── UserPreferencesRepository.kt  # 사용자 설정 Repository
├── database/
│   ├── AppDatabase.kt                # Room Database
│   ├── TermDao.kt                    # 용어 DAO
│   └── FavoriteRepositoryDao.kt      # 즐겨찾기 DAO
└── ui/
    ├── ByteCodingFragment.kt         # 바이트코딩 화면
    ├── GithubFragment.kt             # GitHub 화면
    ├── DictionaryFragment.kt         # 용어사전 화면
    ├── RepositoryAdapter.kt          # 저장소 어댑터
    └── TermAdapter.kt                # 용어 어댑터
```

## 빌드 요구사항

- **Android Studio**: Hedgehog (2023.1.1) 이상
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Gradle**: 8.2
- **Kotlin**: 1.9.20
- **JDK**: 17

## 설치 및 실행

### 1. 저장소 클론
```bash
git clone https://github.com/saintgo7/app-android.git
cd app-android
```

### 2. Android Studio에서 프로젝트 열기
- Android Studio 실행
- File → Open → 프로젝트 디렉토리 선택

### 3. Gradle 동기화
- Android Studio가 자동으로 Gradle 동기화 시작
- 필요한 의존성 자동 다운로드

### 4. 앱 실행
- 에뮬레이터 또는 실제 기기 연결
- Run 버튼 클릭 (Shift + F10)

## 사용 라이브러리

### Android Jetpack
- `androidx.core:core-ktx:1.12.0`
- `androidx.appcompat:appcompat:1.6.1`
- `androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0`
- `androidx.lifecycle:lifecycle-livedata-ktx:2.7.0`

### UI Components
- `com.google.android.material:material:1.11.0`
- `androidx.constraintlayout:constraintlayout:2.1.4`
- `androidx.viewpager2:viewpager2:1.0.0`
- `androidx.recyclerview:recyclerview:1.3.2`
- `androidx.cardview:cardview:1.0.0`

### Room Database
- `androidx.room:room-runtime:2.6.1`
- `androidx.room:room-ktx:2.6.1`
- `androidx.room:room-compiler:2.6.1` (KSP)

### DataStore
- `androidx.datastore:datastore-preferences:1.0.0`

### Navigation
- `androidx.navigation:navigation-fragment-ktx:2.7.6`
- `androidx.navigation:navigation-ui-ktx:2.7.6`

### Networking
- `com.squareup.retrofit2:retrofit:2.9.0`
- `com.squareup.retrofit2:converter-gson:2.9.0`
- `com.squareup.okhttp3:okhttp:4.12.0`
- `com.squareup.okhttp3:logging-interceptor:4.12.0`
- `com.google.code.gson:gson:2.10.1`

### Coroutines
- `org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3`
- `org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3`

### Testing
- `junit:junit:4.13.2`
- `androidx.test.ext:junit:1.1.5`
- `androidx.test.espresso:espresso-core:3.5.1`

## 주요 기능 사용 가이드

### 바이트코딩 도구
1. **진법 변환**: 10진수 입력 → 변환 버튼 클릭
2. **비트 연산**: 두 숫자 입력 → 원하는 연산 버튼 클릭
3. **비트 카운트**: 숫자 하나 입력 → "1의 개수" 버튼 클릭

### GitHub 관리
1. 사용자명 입력 (예: "torvalds")
2. "사용자 검색" 버튼 클릭
3. 프로필 정보 및 저장소 목록 확인

### 용어사전
1. 카테고리 선택 (드롭다운)
2. 검색어 입력으로 실시간 필터링
3. + 버튼으로 새 용어 추가
4. 휴지통 아이콘으로 용어 삭제

### 다크 모드
1. 우측 상단 메뉴 (⋮) 클릭
2. "다크 모드" 선택
3. 앱이 자동으로 테마 전환

## 개발 노트

### 데이터베이스 마이그레이션
Room Database 스키마 변경 시 마이그레이션이 필요합니다:
```kotlin
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // 마이그레이션 코드
    }
}
```

### API 요청 제한
GitHub API는 인증 없이 시간당 60회로 제한됩니다. 프로덕션 환경에서는 Personal Access Token 사용을 권장합니다.

## 향후 계획

- [ ] GitHub 저장소 상세 정보 보기
- [ ] 즐겨찾기 저장소 관리
- [ ] 용어 수정 기능
- [ ] 학습 진도 추적
- [ ] 알고리즘 문제 풀이 기능
- [ ] 백업 및 복원 기능
- [ ] 클라우드 동기화

## 문제 해결

### 빌드 오류
```bash
./gradlew clean
./gradlew build
```

### Room Database 초기화
```bash
adb shell pm clear com.csstudent.manager
```

## 기여 방법

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 라이선스

MIT License

Copyright (c) 2024 CS Student Manager Team

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

## 개발자

CS Student Manager Team

## 연락처

프로젝트에 대한 질문이나 제안사항이 있으시면 Issue를 생성해주세요.
